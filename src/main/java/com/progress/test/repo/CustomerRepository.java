package com.progress.test.repo;

import com.progress.test.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findCustomerById(String id);

    boolean existsByAddress(String address);

    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers();


    @Query("SELECT c FROM Customer c")
    Page<Customer> findAllCustomersWithPagination(Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE Lower(c.name) LIKE %:searchText%")
    Page<Customer> searchCustomers(@Param("searchText") String searchText, Pageable pageable);

}
