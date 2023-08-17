package com.itblee.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntityAudit {

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "company")
    private String company;

    @Column(name = "demand")
    private String demand;

    @Column(name = "note")
    private String note;

    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToMany()
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private Set<User> supportUsers = new HashSet<>();

    @OneToMany(mappedBy = "customer",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

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

    public Set<User> getSupportUsers() {
        return supportUsers;
    }

    public void setSupportUsers(Set<User> supportUsers) {
        this.supportUsers = supportUsers;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void active() {
        status = 1;
    }

    public void disable() {
        status = 0;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addUser(User user) {
        this.supportUsers.add(user);
        user.getAssignCustomers().add(this);
    }

    public void removeUser(User user) {
        this.supportUsers.remove(user);
        user.getAssignCustomers().remove(this);
    }

    public void addTransaction(Transaction transaction) {
        transaction.setCustomer(this);
        this.transactions.add(transaction);
    }

    public void removeTransaction(Transaction transaction) {
        transaction.setCustomer(null);
        this.transactions.remove(transaction);
    }

}
