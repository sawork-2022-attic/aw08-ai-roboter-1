package com.micropos.delivery.mapper;

import com.micropos.dto.ItemDto;
import com.micropos.model.Item;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper
public interface ItemMapper {
    ItemDto toItemDto(Item item);

    Item toItem(ItemDto itemDto);

//    Item toItem(ItemFiledDto itemFiledDto);

    List<ItemDto> toItemDtos(Collection<Item> items);
}
