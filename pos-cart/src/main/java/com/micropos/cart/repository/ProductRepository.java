package com.micropos.cart.repository;


import com.micropos.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> allProducts();

    Product getProduct(String productId);

}