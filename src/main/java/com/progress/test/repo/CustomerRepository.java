package com.progress.test.repo;

import com.progress.test.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findById(String id);

    boolean existsByAddress(String address);

}
