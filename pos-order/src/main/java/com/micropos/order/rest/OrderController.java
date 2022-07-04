package com.micropos.order.rest;

import com.micropos.api.OrderApi;
import com.micropos.dto.CartDto;
import com.micropos.dto.OrderDto;
import com.micropos.order.mapper.CartMapper;
import com.micropos.order.mapper.OrderMapper;
import com.micropos.model.Cart;
import com.micropos.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class OrderController implements OrderApi {

    private OrderService orderService;

    private OrderMapper orderMapper;

    private CartMapper cartMapper;

    @Autowired
    public void setOrderService(OrderService orderService) {this.orderService = orderService;}

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    @Override
    public ResponseEntity<OrderDto> getOrder(Long orderId) {
        var order = orderService.getOrder(Math.toIntExact(orderId));
        if (order.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderMapper.toOrderDto(order.get()));
    }

    @Override
    public ResponseEntity<OrderDto> createOrder(CartDto cartDto) {
        Cart cart = cartMapper.toCart(cartDto);
        var order = orderService.createOrder(cart);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        orderService.delivery(order.get().id());
        return ResponseEntity.ok(orderMapper.toOrderDto(order.get()));
    }


    @RequestMapping(value = "/")
    public String test() {
        return orderService.test();
    }
}
