package com.micropos.counter.rest;

import com.micropos.api.BillApi;
import com.micropos.counter.service.CounterService;
import com.micropos.dto.MessageDto;
import com.micropos.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController implements BillApi {

    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService) {
        this.counterService = counterService;
    }

    public ResponseEntity<MessageDto> createBill(OrderDto orderDto) {
        MessageDto messageDto = new MessageDto();
        if (counterService.createBill(Math.toIntExact(orderDto.getId()))) {
            messageDto.message("创建成功");
            messageDto.setSuccess(true);
        } else {
            messageDto.message("创建失败");
            messageDto.setSuccess(false);
        }
        return ResponseEntity.ok(messageDto);
    }
}
