package com.micropos.cart.rest;

import com.micropos.api.ItemApi;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.mapper.OrderMapper;
import com.micropos.cart.service.PosService;
import com.micropos.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
@CrossOrigin
public class CartController implements ItemApi {

    private final PosService posService;

    private CartMapper cartMapper;

    private OrderMapper orderMapper;

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    public CartController(PosService service) {
        this.posService = service;
    }

    @Override
    public ResponseEntity<CartDto> showCartItems() {
        var cart = posService.getCart();
        var cartDto = cartMapper.toCartDto(cart);
        return ResponseEntity.ok(cartDto);
    }

    @Override
    public ResponseEntity<ItemDto> addItem(Integer productId, ItemFiledDto itemFiledDto) {
        int quantity = itemFiledDto.getQuantity();
        return ResponseEntity.ok(cartMapper.toItemDto(posService.add(productId, quantity)));
    }

    @Override
    public ResponseEntity<ItemDto> deleteItem(Integer productId) {
        return ResponseEntity.ok(cartMapper.toItemDto(posService.removeItem(productId)));
    }

    @Override
    public ResponseEntity<ItemDto> updateItem(Integer productId, ItemFiledDto itemFiledDto) {
        return ResponseEntity.ok(cartMapper.toItemDto(posService.modifyItem(productId, itemFiledDto.getQuantity())));
    }

    @Override
    public ResponseEntity<MessageDto> deleteAllItems() {
        MessageDto messageDto = new MessageDto();
        if (posService.removeAllItem()) {
            messageDto.setSuccess(true);
            messageDto.setMessage("delete successes");
        } else {
            messageDto.setSuccess(false);
            messageDto.setMessage("fail");
        }
        return ResponseEntity.ok(messageDto);
    }

    @Override
    public ResponseEntity<OrderDto> convertCartToOrder(CartDto cartDto) {
        var cart = cartMapper.toCart(cartDto);
        var order = posService.createOrder(cart);
        var orderDto = orderMapper.toOrderDto(order);
        deleteAllItems();
        return ResponseEntity.ok(orderDto);
    }
}
