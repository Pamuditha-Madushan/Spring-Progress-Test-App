package com.progress.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHasItemDto {

    private String orderOrderId;
    private Integer itemCode;
    private Double unitPrice;
    private Integer qty;
}
