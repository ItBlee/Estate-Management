package com.itblee.service.impl;

import com.itblee.converter.CustomerConverter;
import com.itblee.dto.*;
import com.itblee.repository.CustomerRepository;
import com.itblee.repository.entity.Customer;
import com.itblee.repository.entity.Transaction;
import com.itblee.repository.entity.User;
import com.itblee.repository.enums.TransactionType;
import com.itblee.repository.sqlbuilder.SqlMap;
import com.itblee.repository.sqlbuilder.impl.LinkedSqlMap;
import com.itblee.repository.sqlbuilder.key.CustomerKey;
import com.itblee.security.utils.SecurityUtils;
import com.itblee.service.CustomerService;
import com.itblee.utils.MapUtils;
import com.itblee.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.itblee.constant.SystemConstant.MANAGER;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Map<String, String> TRANSACTION_TYPES = MapUtils.of(TransactionType.values());

    private static final int DONE = 2;
    private static final int TODO = 1;
    private static final int DISABLE = 0;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public Optional<CustomerDTO> findById(Long id) {
        Optional<Customer> customer;
        if (!SecurityUtils.hasRole(MANAGER)) {
            Long authId = SecurityUtils.getPrincipal().getId();
            customer = customerRepository.findByIdAndSupportUsers_Id(id, authId);
        } else customer = customerRepository.findById(id);
        return customer.map(customerConverter::toDto);
    }

    @SuppressWarnings("unchecked")
    private SqlMap<CustomerKey> mapKey(Map<?, ?> params) {
        ValidateUtils.requireValidParams(params);
        SqlMap<CustomerKey> statements = new LinkedSqlMap<>();
        statements.putAll(params, CustomerKey.class);
        statements.put(CustomerKey.STATUS, new Integer[] {TODO, DONE});
        if (!SecurityUtils.hasRole(MANAGER)) {
            Long authId = SecurityUtils.getPrincipal().getId();
            Object staffVal = statements.get(CustomerKey.STAFF);
            if (staffVal instanceof Collection) {
                ((Collection<Long>) staffVal).add(authId);
            } else {
                statements.put(CustomerKey.STAFF, new ArrayList<Long>() {{
                    add((Long) staffVal);
                    add(authId);
                }});
            }
        }
        return statements;
    }

    @Override
    public List<CustomerListDTO> findAll(Map<?, ?> params, Pageable pageable) {
        SqlMap<CustomerKey> statements = mapKey(params);
        statements.addScope(CustomerKey.ALL);
        List<Customer> results = customerRepository.findByCondition(statements, pageable);
        return customerConverter.toListDto(results);
    }

    @Override
    public int countAll(Map<?, ?> params) {
        SqlMap<CustomerKey> statements = mapKey(params);
        statements.addScope(CustomerKey.COUNT);
        return customerRepository.countByCondition(statements);
    }

    @Override
    @Transactional
    public void assignCustomer(Long customerId, Long[] staffIds) {
        Customer customer = customerRepository.findById(customerId).get();
        List<User> assignUsers = Arrays.stream(staffIds)
                .map(User::newInstance)
                .collect(Collectors.toList());
        customer.getSupportUsers().retainAll(assignUsers);
        customer.getSupportUsers().addAll(assignUsers);
    }

    @Override
    public void changeStatus(Long id) {
        Customer customer = customerRepository.findById(id).get();
        switch (customer.getStatus()) {
            case TODO:
                customer.setStatus(DONE);
                break;
            case DONE:
                customer.setStatus(TODO);
                break;
        }
        customerRepository.save(customer);
    }

    @Override
    public Map<String, String> getTransactionTypes() {
        return TRANSACTION_TYPES;
    }

    @Override
    @Transactional
    public void saveTransaction(TransactionDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId()).get();
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.valueOf(dto.getTypeCode()));
        transaction.setNote(dto.getNote());
        customer.addTransaction(transaction);
    }

    @Override
    public void fillSource(PagedListHolder<CustomerListDTO> pages, final CustomerSearchDTO dto) {
        Map<?, ?> params = MapUtils.from(dto, true);
        pages.setTotalItems(countAll(params));
        while (true) {
            Pageable pageable = PageRequest.of(pages.getPage() - 1, pages.getPageSize());
            List<CustomerListDTO> customers = findAll(params, pageable);
            if (customers.isEmpty() && pages.isNotFirstPage()) {
                pages.toLastPage();
                continue;
            }
            pages.setSource(customers);
            break;
        }
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO dto) {
        Customer customer = customerConverter.toEntity(dto);
        customer.setStatus(TODO);
        if (customer.getId() != null) {
            customerRepository.findById(customer.getId()).ifPresent(oldCustomer -> {
                if (!SecurityUtils.hasRole(MANAGER))
                    customer.setFullName(oldCustomer.getFullName());
                customer.getTransactions().addAll(oldCustomer.getTransactions());
                customer.setSupportUsers(oldCustomer.getSupportUsers());
                customer.setStatus(oldCustomer.getStatus());
            });
        }
        return customerConverter.toDto(customerRepository.save(customer));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        List<Long> customerIds = Arrays.stream(ids).boxed().collect(Collectors.toList());
        for (Customer customer : customerRepository.findByIdIn(customerIds)) {
            customer.setStatus(DISABLE);
        }
    }

}
