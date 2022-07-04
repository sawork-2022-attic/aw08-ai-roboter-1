package com.micropos.delivery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.delivery.repository.DeliveryRepository;
import com.micropos.model.Delivery;
import com.micropos.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryServiceImp implements DeliveryService {

    private DeliveryRepository deliveryRepository;

    private static final String QUEUE_NAME = "OrderQueue";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setDeliveryRepository(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Optional<Delivery> createDelivery(Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        for(var item: order.getOrderItems()) {
            stringBuilder.append(item.product().name()).append(" ").append(item.quantity()).append("\n");
        }
        Delivery delivery = new Delivery();
        delivery.information(stringBuilder.toString());
        deliveryRepository.save(delivery);
        return Optional.of(delivery);
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(String in){
        try {
            Order order = objectMapper.readValue(in, Order.class);
            createDelivery(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Delivery> getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId);
    }
}
