package com.example.db_bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @Column(nullable = false)
    private Long quantity;

    @Column(name = "item_price", nullable = false, length = 7)
    private String itemPrice;


    public OrderItem() {}

    public OrderItem(Long id, Long quantity, String itemPrice) {
        this.id = id;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public OrderItem(Long id, Book book, Long quantity, String itemPrice) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public OrderItem(Long id, Book book, Order order, Long quantity, String itemPrice) {
        this.id = id;
        this.book = book;
        this.order = order;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", book=" + book +
                ", order=" + order +
                ", quantity=" + quantity +
                ", itemPrice='" + itemPrice + '\'' +
                '}';
    }
}
