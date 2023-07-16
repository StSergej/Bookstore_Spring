package com.example.db_bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_date", nullable = false, length = 10)
    private String orderDate;

    @Column(name = "total_amount", nullable = false, length = 10)
    private String totalAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    //@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;



    public Order() {}

    public Order(Long id, String orderDate, String totalAmount) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public Order(Long id, String orderDate, String totalAmount, Customer customer) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate='" + orderDate + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", customer=" + customer + '}';
    }
}
