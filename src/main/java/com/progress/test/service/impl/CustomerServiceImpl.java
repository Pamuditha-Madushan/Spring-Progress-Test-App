package com.progress.test.service.impl;

import com.progress.test.dto.CustomerDto;
import com.progress.test.entity.Customer;
import com.progress.test.repo.CustomerRepository;
import com.progress.test.service.process.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(customer);
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public boolean isDuplicateCustomer(CustomerDto customerDto) {
        return customerRepository.existsByAddress(customerDto.getAddress());
    }

    @Override
    public Optional<CustomerDto> findById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(c -> modelMapper.map(c, CustomerDto.class));
    }
}
