package com.micropos.cart.mapper;

import com.micropos.dto.DeliveryDto;
import com.micropos.model.Delivery;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryMapper{
    Delivery toDelivery(DeliveryDto deliveryDto);

    DeliveryDto toDeliveryDto(Delivery delivery);
}
