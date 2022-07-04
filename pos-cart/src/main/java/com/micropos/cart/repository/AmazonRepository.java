package com.micropos.cart.repository;


import com.micropos.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AmazonRepository extends PagingAndSortingRepository<Product, Integer> {

}
