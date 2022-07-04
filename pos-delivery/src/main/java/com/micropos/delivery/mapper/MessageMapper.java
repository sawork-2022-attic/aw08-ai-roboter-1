package com.micropos.delivery.mapper;

import com.micropos.dto.MessageDto;
import com.micropos.model.Message;
import org.mapstruct.Mapper;


@Mapper
public interface MessageMapper {
    MessageDto toMessageDto(Message message);
}
