package com.augusto.logapi.domain.service;

import com.augusto.logapi.domain.exception.DomainException;
import com.augusto.logapi.domain.model.Client;
import com.augusto.logapi.domain.model.Delivery;
import com.augusto.logapi.domain.model.StatusDelivery;
import com.augusto.logapi.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class RequestDeliveryService {

    private DeliveryRepository deliveryRepository;
    private CatalogClientService catalogClientService;

    @Transactional
    public Delivery request(Delivery delivery) {
        Client client = catalogClientService.find(delivery.getClient().getId());

        delivery.setClient(client);
        delivery.setStatus(StatusDelivery.PENDING);
        delivery.setDateOrder(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }
}
