package com.itblee.repository;

import com.itblee.repository.custom.CustomerRepositoryCustom;
import com.itblee.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {
    Set<Customer> findByIdIn(Iterable<Long> ids);
    Optional<Customer> findByIdAndSupportUsers_Id(Long id, Long staffId);
}
