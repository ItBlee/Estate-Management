package com.itblee.repository.custom;

import com.itblee.repository.entity.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CustomerRepositoryCustom {
    List<Customer> findByCondition(Map<?, ?> conditions, Pageable pageable);
    int countByCondition(Map<?, ?> conditions);
}
