package com.itblee.repository.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntityAudit {

    private static final long serialVersionUID = -4614466784491490067L;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToMany(mappedBy = "assignUsers")
    private Set<Building> assignBuildings = new HashSet<>();

    @ManyToMany(mappedBy = "supportUsers")
    private Set<Customer> assignCustomers = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "roleid", nullable = false))
    private Set<Role> roles = new HashSet<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Building> getAssignBuildings() {
        return assignBuildings;
    }

    public void setAssignBuildings(Set<Building> assignBuildings) {
        this.assignBuildings = assignBuildings;
    }

    public Set<Customer> getAssignCustomers() {
        return assignCustomers;
    }

    public void setAssignCustomers(Set<Customer> assignCustomers) {
        this.assignCustomers = assignCustomers;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void active() {
        status = 1;
    }

    public void disable() {
        status = 0;
    }

    public void addBuilding(Building building) {
        this.getAssignBuildings().add(building);
        building.getAssignUsers().add(this);
    }

    public void removeBuilding(Building building) {
        this.getAssignBuildings().remove(building);
        building.getAssignUsers().remove(this);
    }

    public void addCustomer(Customer customer) {
        this.getAssignCustomers().add(customer);
        customer.getSupportUsers().add(this);
    }

    public void removeBuilding(Customer customer) {
        this.getAssignCustomers().remove(customer);
        customer.getSupportUsers().remove(this);
    }

    public void addRole(Role role) {
        this.getRoles().add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        this.getRoles().remove(role);
        role.getUsers().remove(this);
    }

    public static User newInstance(long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

}
