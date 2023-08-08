package com.progress.test.dto.response.custom;

import lombok.Data;

@Data
public class CustomResponse<Target> {

    private int responseCode;
    private boolean success;
    private String message;
    private Target data;
}
