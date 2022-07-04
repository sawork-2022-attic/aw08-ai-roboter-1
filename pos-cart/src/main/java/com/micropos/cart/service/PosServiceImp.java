package com.micropos.cart.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.cart.mapper.CartMapper;
import com.micropos.cart.repository.AmazonRepository;
import com.micropos.dto.CartDto;
import com.micropos.model.Cart;
import com.micropos.model.Item;
import com.micropos.model.Order;
import com.micropos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class PosServiceImp implements PosService, Serializable {

    private AmazonRepository amazonRepository;

    private Cart cart;

    private RestTemplate restTemplate;

    private String ORDER_SERVER_URL = "http://CART-SERVICE/api";

    private CartMapper cartMapper;

    @Autowired
    public void setOrderMapper(CartMapper orderMapper) {
        this.cartMapper = orderMapper;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setDb(AmazonRepository amazonRepository) {
        this.amazonRepository = amazonRepository;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

//    @Override
//    public Product randomProduct() {
//        return products().get(ThreadLocalRandom.current().nextInt(0, products().size()));
//    }

    @Override
    public Item getItem(Integer productId) {
        return getItems().stream()
                            .filter(item -> item.product().id().equals(productId))
                            .findFirst().orElse(null);
    }

    @Override
    public void checkout(Cart cart) {

    }

    @Override
    public Item add(Product product, int amount) {
        return add(product.id(), amount);
    }

    @Override
    public Item add(Integer productId, int amount) {

        var  product = amazonRepository.findById(productId);
        if (product.isEmpty()) return null;
        var item = new Item();
        item.product(product.get());
        item.quantity(amount);
        return cart.addItem(item);
    }


//    @Override
//    public List<Product> products() {
//        System.out.println("get products");
//        return amazonRepository.
//    }

    @Override
    public List<Item> getItems() {
        if (cart == null)
            cart = new Cart();
        return cart.getItems();
    }

    @Override
    public Item removeItem(Integer productId) {
        var items = getItems();
        var target = items.stream().filter(item -> item.product().id().equals(productId))
                .findFirst().orElse(null);
        if (target == null)
            return null;

        items.remove(target);
        return target;
    }

    @Override
    public Item modifyItem(Integer productId, int quantity) {
        if (cart.getItem(productId) != null) {
            return cart.modifyItem(productId, quantity);
        } else {
            return add(productId, quantity);
        }
    }

    @Override
    public boolean removeAllItem() {
        getCart().setItems(new ArrayList<>());
        return true;
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public Order createOrder(Cart cart) {
        CartDto cartDto = cartMapper.toCartDto(cart);
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = null;
        try {
            request = new HttpEntity<>(mapper.writeValueAsString(cartDto), headers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return restTemplate.postForObject(ORDER_SERVER_URL + "/createOrder", request, Order.class);
    }
}
