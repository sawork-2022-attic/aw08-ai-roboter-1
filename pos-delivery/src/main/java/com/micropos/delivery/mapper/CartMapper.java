package com.micropos.delivery.mapper;


import com.micropos.dto.CartDto;
import com.micropos.dto.CartItemDto;
import com.micropos.dto.ItemDto;
import com.micropos.model.Cart;
import com.micropos.model.Item;
import org.mapstruct.Mapper;

@Mapper
public interface CartMapper {
    CartDto toCartDto(Cart cart);

    Cart toCart(CartDto cartDto);
    Item toItem(ItemDto value);

}
