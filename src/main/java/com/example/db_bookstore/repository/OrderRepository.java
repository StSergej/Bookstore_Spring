package com.example.db_bookstore.repository;

import com.example.db_bookstore.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
