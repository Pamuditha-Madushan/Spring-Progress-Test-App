package com.progress.test.dto.response.jwt;

import lombok.Data;

@Data
public class JwtResponse<T>{

    private int responseCode;
    private boolean success;
    private String message;
    private T token;
}
