package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
// Users table is 'users'
@Table(name = "users")
public class Users {
    // I used the users ID as the user email and I identify this variable as 'users_email'
    @Id
    @Column(name = "users_email", nullable = false, length = 128)
    private String id;

    // this column will store the user's name
    @Column(name = "user_name", nullable = false, length = 128)
    private String name;

    // this column will store the user's address
    @Column(name = "address", nullable = false, length = 128)
    private String address;

    // this column will store the user's phone number
    @Column(name = "phone", nullable = false, length = 128)
    private String phone;

    // this column will store the user's password
    @Column(name = "password", nullable = false, length = 128)
    private String password;

    // Create a connection between the 'orders' table and the 'users' table
    // and if the item is deleted from the table then it will be deleted from the other one too
    // but not from the order_detailed_list so in the statistics it will be available forever
    // this connection is necesary because of the statistics
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "usersEmail", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE})
    private Set<Orders> orders = new LinkedHashSet<>();

    public Users() {
    }

    public Users(String id, String name, String address, String phone, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public Set<Orders> getOrder() {
        return orders;
    }

    public void setOrder(Set<Orders> orders) {
        this.orders = orders;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}