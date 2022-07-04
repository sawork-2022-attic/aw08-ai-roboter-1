package com.micropos.products.repository;


import com.micropos.products.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> allProducts();

    Product findProduct(String productId);

}