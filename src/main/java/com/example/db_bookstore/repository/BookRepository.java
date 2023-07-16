package com.example.db_bookstore.repository;

import com.example.db_bookstore.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
