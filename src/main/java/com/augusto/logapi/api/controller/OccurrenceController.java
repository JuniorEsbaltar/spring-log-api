package com.augusto.logapi.api.controller;

import com.augusto.logapi.api.assembler.OccurrenceAssembler;
import com.augusto.logapi.api.model.OccurrenceModel;
import com.augusto.logapi.api.model.input.OccurrenceInput;
import com.augusto.logapi.domain.model.Delivery;
import com.augusto.logapi.domain.model.Occurrence;
import com.augusto.logapi.domain.service.FindDeliveryService;
import com.augusto.logapi.domain.service.RegisterOccurrenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
public class OccurrenceController {

    private RegisterOccurrenceService registerOccurrenceService;
    private FindDeliveryService findDeliveryService;
    private OccurrenceAssembler occurrenceAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceModel register(
            @PathVariable Long deliveryId,
            @Valid @RequestBody OccurrenceInput occurrenceInput
    ) {
        Occurrence occurrence = registerOccurrenceService.register(deliveryId, occurrenceInput.getDescription());

        return occurrenceAssembler.toModel(occurrence);
    }

    @GetMapping
    public List<OccurrenceModel> occurrenceModelList(@PathVariable Long deliveryId) {
        Delivery delivery = findDeliveryService.find(deliveryId);

        return occurrenceAssembler.toCollection(delivery.getOccurrenceList());
    }
}
