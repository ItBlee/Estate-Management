package com.itblee.dto;

import com.itblee.repository.enums.TransactionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerDTO implements Serializable {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String company;
    private String demand;
    private String note;
    private Integer status;
    private Map<TransactionType, List<TransactionDTO>> transactionMap;

    public CustomerDTO() {
        transactionMap = new LinkedHashMap<>();
        for (TransactionType value : TransactionType.values()) {
            transactionMap.put(value, new ArrayList<>());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<TransactionType, List<TransactionDTO>> getTransactionMap() {
        return transactionMap;
    }

    public void setTransactionMap(Map<TransactionType, List<TransactionDTO>> transactionMap) {
        this.transactionMap = transactionMap;
    }

}
