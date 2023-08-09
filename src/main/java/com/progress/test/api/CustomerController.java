package com.progress.test.api;

import com.progress.test.dto.CustomerDto;
import com.progress.test.dto.UserDto;
import com.progress.test.dto.response.custom.CustomResponse;
import com.progress.test.service.process.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<CustomerDto>> saveCustomer(@RequestBody CustomerDto customerDto) {

        if (customerService.isDuplicateCustomer(customerDto)) {
            CustomResponse<CustomerDto> customResponse = createErrorCustomResponse(HttpStatus.BAD_REQUEST, "Customer with the same address already exists already !!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customResponse);
        }

        CustomerDto createCustomer = customerService.saveCustomer(customerDto);
        CustomResponse<CustomerDto> customResponse = createSuccessCustomResponse(createCustomer, HttpStatus.CREATED, "Customer created successfully ...");
        return ResponseEntity.status(HttpStatus.CREATED).body(customResponse);
    }

    @GetMapping("/findById")
    public ResponseEntity<CustomResponse<CustomerDto>> getCustomerById(@RequestParam String id) {
        return customerService.findById(id)
                .map(customer -> ResponseEntity.ok(createSuccessCustomResponse(customer, HttpStatus.OK, "Customer retrieved successfully ...")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorCustomResponse(HttpStatus.NOT_FOUND, "Customer not found !!")));


    }




    private <T> CustomResponse<T> createSuccessCustomResponse(T data, HttpStatus status, String message) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setResponseCode(status.value());
        customResponse.setSuccess(true);
        customResponse.setMessage(message);
        customResponse.setData(data);
        return customResponse;
    }

    private <T> CustomResponse<T> createErrorCustomResponse(HttpStatus status, String message) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setResponseCode(status.value());
        customResponse.setSuccess(false);
        customResponse.setMessage(message);
        return customResponse;
    }


}
