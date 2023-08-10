package com.progress.test.advices;

import com.progress.test.dto.response.custom.CustomResponse;
import com.progress.test.exception.NoSuchElementException;
import com.progress.test.util.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseResponse {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CustomResponse<Void>> handleNoSuchElementException(NoSuchElementException ex) {
       CustomResponse<Void> errorCustomResponse = CustomResponse.errorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCustomResponse);
    }


}
