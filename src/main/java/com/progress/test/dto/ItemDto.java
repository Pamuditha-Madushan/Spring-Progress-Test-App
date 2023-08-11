package com.progress.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Integer code;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private byte[] barcode;
}
