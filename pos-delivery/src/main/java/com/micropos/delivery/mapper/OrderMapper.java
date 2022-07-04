package com.micropos.delivery.mapper;

import com.micropos.dto.OrderDto;
import com.micropos.model.Order;
import org.mapstruct.Mapper;


@Mapper
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
    Order toOrder(OrderDto orderDto);
}
