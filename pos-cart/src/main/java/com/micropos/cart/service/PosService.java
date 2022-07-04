package com.micropos.cart.service;


import com.micropos.model.Cart;
import com.micropos.model.Item;
import com.micropos.model.Order;
import com.micropos.model.Product;

import java.util.List;

public interface PosService {

    void checkout(Cart cart);

    Item add(Product product, int amount);

    Item add(Integer productId, int amount);

//    List<Product> products();

//    Product randomProduct();

    Item getItem(Integer productId);

    List<Item> getItems();

    Item removeItem(Integer ProductId);

    Item modifyItem(Integer productId, int quantity);

    boolean removeAllItem();

    Cart getCart();

    Order createOrder(Cart cart);
}
