package com.progress.test.dto.response.custom;

import lombok.Data;

@Data
public class CustomResponse<T> {

    private int responseCode;
    private boolean success;
    private String message;
    private T data;
}
