package com.progress.test.api;

import com.progress.test.dto.ItemDto;
import com.progress.test.dto.OrderDto;
import com.progress.test.dto.OrderHasItemDto;
import com.progress.test.dto.request.OrderCreationRequest;
import com.progress.test.dto.response.custom.CustomResponse;
import com.progress.test.exception.NoSuchElementException;
import com.progress.test.service.process.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/business/create")
    public ResponseEntity<?> saveOrder(
            @RequestBody OrderCreationRequest orderCreationRequest) {

       try {
            OrderDto savedOrder = orderService.createOrder(orderCreationRequest.getOrderDto(), orderCreationRequest.getItemDtoList());

            CustomResponse<OrderDto> customSuccessResponse = CustomResponse.successResponse(
                    HttpStatus.OK.value(),
                    "Order saved successfully ...",
                    savedOrder
            );
            return ResponseEntity.ok(customSuccessResponse);

        } catch (NoSuchElementException ex) {
           CustomResponse<Void> customErrorResponse = CustomResponse.errorResponse(
                   HttpStatus.NOT_FOUND.value(),
                   ex.getMessage()
           );
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorResponse);
       } catch (Exception ex) {
           CustomResponse<Void> customErrorResponse = CustomResponse.errorResponse(
                   HttpStatus.INTERNAL_SERVER_ERROR.value(),
                   ex.getMessage()
           );
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customErrorResponse);
       }
    }
}
