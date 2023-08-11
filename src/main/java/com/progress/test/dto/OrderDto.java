package com.progress.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String orderId;
    private Date orderDate;
    private Double cost;
    private String customerId;
    private String userId;
}
