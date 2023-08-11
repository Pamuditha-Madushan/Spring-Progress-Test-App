package com.progress.test.service.impl;

import com.progress.test.dto.CustomerDto;
import com.progress.test.entity.Customer;
import com.progress.test.exception.NoSuchElementException;
import com.progress.test.repo.CustomerRepository;
import com.progress.test.service.process.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<CustomerDto> findCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(c -> modelMapper.map(c, CustomerDto.class));
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAllCustomers();
        return customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(String id, CustomerDto customerDto) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(customerDto, customer);
            customerRepository.save(customer);
            return modelMapper.map(customer, CustomerDto.class);
        } else {
            throw new NoSuchElementException("Customer not found with ID : " + id);
        }

    }


    @Override
    public Page<CustomerDto> findAllCustomersWithPagination(Pageable pageable) {
        Page<Customer> paginatedCustomers = customerRepository.findAllCustomersWithPagination(pageable);
        return paginatedCustomers
                .map(this::convertToDto);
    }

    @Override
    public Page<CustomerDto> searchCustomers(String searchText, Pageable pageable) {
        Page<Customer> searchedCustomers = customerRepository.searchCustomers(searchText, pageable);
        return searchedCustomers
                .map(this::convertToDto);
    }


    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found" + id));
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setAddress(customer.getAddress());
        customerDto.setSalary(customer.getSalary());
        return customerDto;
    }


}
