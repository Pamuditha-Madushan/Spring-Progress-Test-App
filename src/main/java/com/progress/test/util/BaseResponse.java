package com.progress.test.util;

import com.progress.test.dto.response.custom.CustomResponse;
import org.springframework.http.HttpStatus;

public class BaseResponse {

    protected <T> CustomResponse<T> createCustomSuccessResponse(T data, HttpStatus status, String message) {
        return CustomResponse.successResponse(status.value(), message, data);
    }

    protected  CustomResponse<Void> createCustomErrorResponse(HttpStatus status, String message) {
        return CustomResponse.successResponse(status.value(), message, null);
    }

}
