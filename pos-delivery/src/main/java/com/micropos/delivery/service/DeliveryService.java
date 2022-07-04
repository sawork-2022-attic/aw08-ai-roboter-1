package com.micropos.delivery.service;

import com.micropos.model.Delivery;
import com.micropos.model.Order;

import java.util.Optional;

public interface DeliveryService {

    Optional<Delivery> createDelivery(Order order);

    Optional<Delivery> getDelivery(Long deliveryId);
}
