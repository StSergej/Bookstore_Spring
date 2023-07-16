package com.example.db_bookstore.repository;

import com.example.db_bookstore.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
