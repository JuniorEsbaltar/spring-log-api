package com.augusto.logapi.domain.service;

import com.augusto.logapi.domain.exception.DomainException;
import com.augusto.logapi.domain.model.Delivery;
import com.augusto.logapi.domain.model.StatusDelivery;
import com.augusto.logapi.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class DeliveryFinishService {

    private DeliveryRepository deliveryRepository;
    private FindDeliveryService findDeliveryService;

    @Transactional
    public void finish(Long deliveryId) {
        Delivery delivery = findDeliveryService.find(deliveryId);
        delivery.finish();
        deliveryRepository.save(delivery);
    }
}
