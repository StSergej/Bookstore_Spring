package com.example.db_bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_name", nullable = false, length = 45)
    private String customerName;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 85)
    private String address;


    @OneToMany(mappedBy = "customer")
    private List<Order> order;



    public Customer() {

    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Customer(Long id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public Customer(Long id, String customerName, String phone) {
        this.id = id;
        this.customerName = customerName;
        this.phone = phone;
    }

    public Customer(Long id, String customerName, String email, String phone, String address) {
        this.id = id;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerName, customer.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName);
    }
}
