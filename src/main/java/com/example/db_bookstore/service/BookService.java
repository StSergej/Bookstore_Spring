package com.example.db_bookstore.service;

import com.example.db_bookstore.entities.Book;
import com.example.db_bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> listAllBook(){

        return (List<Book>) bookRepository.findAll();
    }

    public void saveBook(Book book){

        bookRepository.save(book);
    }

    public void deleteBook(Long id){

        bookRepository.deleteById(id);
    }
}
