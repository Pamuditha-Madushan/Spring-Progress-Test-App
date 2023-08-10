package com.progress.test.api;

import com.progress.test.dto.CustomerDto;
import com.progress.test.dto.response.custom.CustomResponse;
import com.progress.test.exception.NoSuchElementException;
import com.progress.test.service.process.CustomerService;
import com.progress.test.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController extends BaseResponse {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<CustomerDto>> saveCustomer(@RequestBody CustomerDto customerDto) {

        if (customerService.isDuplicateCustomer(customerDto)) {
            CustomResponse<CustomerDto> customResponse = createErrorCustomResponse(
                    HttpStatus.BAD_REQUEST, "Customer with the same address already exists already !!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customResponse);
        }

        CustomerDto createCustomer = customerService.saveCustomer(customerDto);
        CustomResponse<CustomerDto> customResponse = createSuccessCustomResponse(
                createCustomer, HttpStatus.CREATED, "Customer created successfully ...");
        return ResponseEntity.status(HttpStatus.CREATED).body(customResponse);
    }

    @GetMapping("/findById")
    public ResponseEntity<CustomResponse<CustomerDto>> getCustomerById(@RequestParam String id) {
        return customerService.findCustomerById(id)
                .map(customer -> ResponseEntity.ok(
                        createSuccessCustomResponse(customer, HttpStatus.OK, "Customer retrieved successfully ...")))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        createErrorCustomResponse(HttpStatus.NOT_FOUND, "Customer not found !!")));


    }


    @GetMapping("/findAllCustomers")
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<CustomerDto> customersList = customerService.findAllCustomers();

            CustomResponse<List<CustomerDto>> customResponse = CustomResponse.successResponse(
                    HttpStatus.OK.value(),
                    "All customers retrieved successfully ...",
                    customersList
            );
            return ResponseEntity.ok(customResponse);

        } catch (NoSuchElementException ex) {
            CustomResponse<Void> errorCustomResponse = CustomResponse.errorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorCustomResponse);

        }
    }

    @PutMapping("/business/update")
    public ResponseEntity<?> updateCustomer(
            @RequestParam String id,
            @RequestBody CustomerDto customerDto
    ) {
       try {
            CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
            CustomResponse<CustomerDto> customResponse = CustomResponse.successResponse(
                    HttpStatus.OK.value(),
                    "Customer updated successfully ...",
                    updatedCustomer
            );
            return ResponseEntity.ok(customResponse);

        } catch (NoSuchElementException ex) {
           CustomResponse<Void> customErrorResponse = CustomResponse.errorResponse(
                   HttpStatus.NOT_FOUND.value(),
                   ex.getMessage()
           );
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorResponse);
       }
    }


    @GetMapping("/business/find-all-customers-by-pagination")
    public ResponseEntity<?> getAllCustomersWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
       try {
            Pageable pageable = PageRequest.of(page, size);
            Page<CustomerDto> totalPaginatedCustomers = customerService.findAllCustomersWithPagination(pageable);

            CustomResponse<Page<CustomerDto>> customResponse = CustomResponse.successResponse(
                    HttpStatus.OK.value(),
                    "Customers of page " + page + " retrieved successfully",
                    totalPaginatedCustomers
            );
            return ResponseEntity.ok(customResponse);

        } catch (Exception ex) {
           CustomResponse<Void> customErrorResponse = CustomResponse.errorResponse(
                   HttpStatus.NOT_FOUND.value(),
                   ex.getMessage()
           );
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorResponse);

       }
    }


    @GetMapping("/business/search-customers")
    public ResponseEntity<?> searchCustomers(
            @RequestParam String searchText,
            @PageableDefault(size = 5, sort = "id") Pageable pageable
    ) {
        try {
            Page<CustomerDto> searchedCustomers = customerService.searchCustomers(searchText, pageable);
            CustomResponse<Page<CustomerDto>> customSuccessResponse = CustomResponse.successResponse(
                    HttpStatus.OK.value(),
                    "Search results retrieved successfully ...",
                    searchedCustomers
            );
            return ResponseEntity.ok(customSuccessResponse);

        } catch (NoSuchElementException ex) {
            CustomResponse<Void> customErrorResponse = CustomResponse.errorResponse(
                    HttpStatus.NOT_FOUND.value(),
                    ex.getMessage()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customErrorResponse);
        }
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
