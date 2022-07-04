package com.micropos.cart.repository;

import com.micropos.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Repository
public class JDRepository implements ProductRepository {
    private List<Product> products = null;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    @Cacheable(value = "JD-Product")
    public List<Product> allProducts() {
        if (products == null) {
            products = products();
        }
        return products;
    }

    @Override
    @Cacheable(value = "JD-Search", key = "#productId")
    public Product getProduct(String productId) {
        for (Product p : allProducts()) {
            if (p.id().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public List<Product> products() {
        String url = "http://product-service/api/product";
        var products = restTemplate.getForObject(url, Product[].class);
        if (products != null) {
            return Arrays.asList(products);
        } else {
            return null;
        }
    }

}

