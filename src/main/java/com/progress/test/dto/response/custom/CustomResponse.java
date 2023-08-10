package com.progress.test.dto.response.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {

    private int responseCode;
    private boolean success;
    private String message;
    private T data;


    public static <T> CustomResponse<T> successResponse(int status, String message, T data) {
        return new CustomResponse<>(status, true, message, data);
    }

    public static CustomResponse<Void> errorResponse(int status, String message) {
        return new CustomResponse<>(status, false, message, null);
    }


}


