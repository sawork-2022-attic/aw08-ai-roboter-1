package com.micropos.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.model.Cart;
import com.micropos.model.Item;
import com.micropos.model.Order;
import com.micropos.model.OrderItem;
import com.micropos.order.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {

    private OrderRepository orderRepository;

//    private final String COUNTER_URL = "http://POS-DELIVERY/counter/";

    private static final String QUEUE_NAME = "OrderQueue";

    @LoadBalanced
    private RestTemplate restTemplate;

    @LoadBalanced
    private RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Optional<Order> createOrder(Cart cart) {
        if (cart.getItems().isEmpty()) {
            return Optional.empty();
        }
        Order order = new Order();
        for (Item item: cart.getItems()) {
            var orderItem = new OrderItem();
            orderItem.product().id(item.product().id());
            orderItem.quantity(item.quantity());
            order.addOrderItem(orderItem);
        }
        orderRepository.save(order);
        return Optional.of(order);
    }

    @Override
    public Optional<Order> delivery(Integer orderId)  {
        var order = orderRepository.findById(orderId);

        if (order.isEmpty())
            return Optional.empty();
        try {
            String orderString = objectMapper.writeValueAsString(order.get());
            rabbitTemplate.convertAndSend(QUEUE_NAME, orderString);
            return order;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Order> getOrder(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    public String test() {
        return String.valueOf(orderRepository.findAll().get(0).getOrderItems().get(1).quantity());
    }
}
