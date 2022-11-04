package com.augusto.logapi.api.controller;

import com.augusto.logapi.api.model.DeliveryModel;
import com.augusto.logapi.api.assembler.DeliveryAssembler;
import com.augusto.logapi.api.model.input.DeliveryInput;
import com.augusto.logapi.domain.model.Delivery;
import com.augusto.logapi.domain.repository.DeliveryRepository;
import com.augusto.logapi.domain.service.DeliveryFinishService;
import com.augusto.logapi.domain.service.RequestDeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private RequestDeliveryService requestDeliveryService;
    private DeliveryRepository deliveryRepository;
    private DeliveryAssembler deliveryAssembler;
    private DeliveryFinishService deliveryFinishService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryModel request(@Valid @RequestBody DeliveryInput deliveryInput) {
        Delivery delivery = deliveryAssembler.toEntity((deliveryInput));
        return deliveryAssembler.toModel(requestDeliveryService.request(delivery));
    }

    @GetMapping
    public List<DeliveryModel> getAll() {
        return deliveryAssembler.toCollectionModel(deliveryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryModel> getById(@PathVariable Long id) {
        return deliveryRepository.findById(id).map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery))
        ).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{deliveryId}/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long deliveryId) {
        deliveryFinishService.finish(deliveryId);
    }
}
