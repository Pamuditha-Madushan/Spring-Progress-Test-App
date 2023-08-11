package com.progress.test.dto.request;

import com.progress.test.dto.ItemDto;
import com.progress.test.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationRequest {

    private OrderDto orderDto;
    private List<ItemDto> itemDtoList;
}
