package com.example.db_bookstore.repository;

import com.example.db_bookstore.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
