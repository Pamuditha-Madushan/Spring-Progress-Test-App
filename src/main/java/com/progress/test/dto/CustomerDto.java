package com.progress.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String id;
    private String name;
    private String address;
    private Double salary;
}
