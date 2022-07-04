package com.micropos.products.service;

import com.micropos.products.model.Product;
import com.micropos.products.repository.AmazonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final AmazonRepository productRepository;

    @Autowired
    public ProductServiceImpl( AmazonRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> products() {
        // 默认返回前20个
        System.out.println("get products");
        return productRepository.findAll(PageRequest.of(1, 20)).getContent();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product randomProduct() {
        return null;
    }
}
