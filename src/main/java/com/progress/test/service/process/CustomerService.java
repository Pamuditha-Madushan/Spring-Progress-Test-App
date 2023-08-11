package com.progress.test.service.process;

import com.progress.test.dto.CustomerDto;
import com.progress.test.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDto saveCustomer(CustomerDto customerDto);

    boolean isDuplicateCustomer(CustomerDto customerDto);

    Optional<CustomerDto> findCustomerById(String id);

    List<CustomerDto> findAllCustomers();

    CustomerDto updateCustomer(String id, CustomerDto customerDto);

    Page<CustomerDto> findAllCustomersWithPagination(Pageable pageable);

    Page<CustomerDto> searchCustomers(String searchText, Pageable pageable);

    Customer getCustomerById(String id);
}
