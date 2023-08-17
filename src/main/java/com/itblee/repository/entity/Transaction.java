package com.itblee.repository.entity;

import com.itblee.repository.enums.TransactionType;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntityAudit {

    @Column(name = "note", nullable = false)
    private String note;

    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
