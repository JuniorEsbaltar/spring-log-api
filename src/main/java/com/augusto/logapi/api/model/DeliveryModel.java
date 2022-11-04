package com.augusto.logapi.api.model;

import com.augusto.logapi.domain.model.StatusDelivery;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryModel {

    private Long id;
    private ClientSummaryModel client;
    private RecipientModel recipient;
    private BigDecimal fee;
    private StatusDelivery status;
    private OffsetDateTime dateOrder;
    private OffsetDateTime dateFinished;
}
