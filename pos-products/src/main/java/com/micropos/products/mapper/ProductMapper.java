package com.micropos.products.mapper;

import com.micropos.dto.ProductDto;
import com.micropos.products.model.Product;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Mapper
public interface ProductMapper {

    default Collection<ProductDto> toProductsDto(Collection<Product> products) {
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for(var product: products) {
            var productDto = new ProductDto();
            productDto.id(product.id()).price(BigDecimal.valueOf(product.price())).image(product.image());
            productDto.name(product.name());
            if (product.price() == 0) {
                // 数据库中存在很多为0的price
                productDto.price(BigDecimal.valueOf(Math.random() * 10 + 10));
            }
            productDtos.add(productDto);
        }
        return productDtos;
    }

    Collection<Product> toProducts(Collection<ProductDto> products);

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product pet);
}
