package com.augusto.logapi.domain.service;

import com.augusto.logapi.domain.exception.DomainException;
import com.augusto.logapi.domain.exception.EntityNotFoundException;
import com.augusto.logapi.domain.model.Delivery;
import com.augusto.logapi.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FindDeliveryService {

    private DeliveryRepository deliveryRepository;

    public Delivery find(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found!"));
    }
}
