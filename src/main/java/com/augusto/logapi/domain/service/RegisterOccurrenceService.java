package com.augusto.logapi.domain.service;

import com.augusto.logapi.domain.exception.DomainException;
import com.augusto.logapi.domain.model.Delivery;
import com.augusto.logapi.domain.model.Occurrence;
import com.augusto.logapi.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegisterOccurrenceService {

    private FindDeliveryService findDeliveryService;

    @Transactional
    public Occurrence register(Long deliveryId, String description) {
        Delivery delivery = findDeliveryService.find(deliveryId);

        return delivery.addOccurrence(description);
    }
}
