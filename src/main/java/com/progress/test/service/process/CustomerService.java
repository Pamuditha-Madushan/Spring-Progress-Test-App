package com.progress.test.service.process;

import com.progress.test.dto.CustomerDto;

import java.util.Optional;

public interface CustomerService {

    CustomerDto saveCustomer(CustomerDto customerDto);

     boolean isDuplicateCustomer(CustomerDto customerDto);
    Optional<CustomerDto> findById(String id);


}
