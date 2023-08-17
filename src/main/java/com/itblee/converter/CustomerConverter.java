package com.itblee.converter;

import com.itblee.dto.CustomerDTO;
import com.itblee.dto.CustomerListDTO;
import com.itblee.dto.TransactionDTO;
import com.itblee.repository.entity.Customer;
import com.itblee.repository.entity.Transaction;
import com.itblee.repository.entity.User;
import com.itblee.utils.ValidateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter extends AbstractConverter {

    public CustomerDTO toDto(Customer entity) {
        ValidateUtils.requireNonNull(entity);
        CustomerDTO dto = convert(entity, CustomerDTO.class);
        for (Transaction transaction : entity.getTransactions()) {
            TransactionDTO transactionDTO = convert(transaction, TransactionDTO.class);
            List<TransactionDTO> transactions = dto.getTransactionMap().get(transaction.getType());
            if (transactions == null)
                dto.getTransactionMap().put(transaction.getType(), new ArrayList<>());
            else transactions.add(transactionDTO);
        }

        return dto;
    }

    public List<CustomerDTO> toDto(Collection<Customer> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CustomerListDTO toListDto(Customer entity) {
        ValidateUtils.requireNonNull(entity);
        CustomerListDTO dto = convert(entity, CustomerListDTO.class);
        String staffs = entity.getSupportUsers().stream()
                .map(User::getFullName)
                .collect(Collectors.joining(", "));
        dto.setStaffs(staffs);
        return dto;
    }

    public List<CustomerListDTO> toListDto(Collection<Customer> entities) {
        return entities.stream()
                .map(this::toListDto)
                .collect(Collectors.toList());
    }


    public Customer toEntity(CustomerDTO dto) {
        ValidateUtils.requireNonNull(dto);
        return convert(dto, Customer.class);
    }

    public List<Customer> toEntity(Collection<CustomerDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
