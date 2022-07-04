package com.micropos.products.repository;


import com.micropos.products.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AmazonRepository extends PagingAndSortingRepository<Product, Long> {

}
