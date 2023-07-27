package com.example.db_bookstore.repository;

import com.example.db_bookstore.entities.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
