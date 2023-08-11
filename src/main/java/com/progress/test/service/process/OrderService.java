package com.progress.test.service.process;

import com.progress.test.dto.ItemDto;
import com.progress.test.dto.OrderDto;
import com.progress.test.dto.OrderHasItemDto;
import com.progress.test.entity.OrderHasItem;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto, List<ItemDto> itemDtoList);
}
