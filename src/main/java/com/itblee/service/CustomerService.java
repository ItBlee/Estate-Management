package com.itblee.service;

import com.itblee.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {
    Optional<CustomerDTO> findById(Long id);
    List<CustomerListDTO> findAll(Map<?, ?> params, Pageable pageable);
    int countAll(Map<?, ?> params);
    CustomerDTO save(CustomerDTO updateDto);
    void delete(long[] ids);
    void assignCustomer(Long customerId, Long[] staffIds);
    void changeStatus(Long id);
    Map<String, String> getTransactionTypes();
    void saveTransaction(TransactionDTO dto);
    void fillSource(PagedListHolder<CustomerListDTO> pages, final CustomerSearchDTO dto);
}
