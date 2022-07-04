package com.micropos.order.service;

import com.micropos.model.Cart;
import com.micropos.model.Order;

import java.util.Optional;

public interface OrderService {

   Optional<Order> createOrder(Cart cart);

   Optional<Order> delivery(Integer orderId);

   Optional<Order> getOrder(Integer orderId);

   //todo
   String test();
}
