package com.micropos.counter.service;

import com.micropos.counter.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CounterServiceImp implements CounterService{

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean createBill(Integer orderId) {
        var orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty())
            return false;
        var order = orderOpt.get();
        order.paid(false);
        orderRepository.save(order);
        return true;
    }
}
