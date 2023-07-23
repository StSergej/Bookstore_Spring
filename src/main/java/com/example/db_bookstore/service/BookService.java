package com.example.db_bookstore.service;

import com.example.db_bookstore.entities.Book;
import com.example.db_bookstore.repository.BookRepository;
import com.example.db_bookstore.service.entityException.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> listAllBook(){

        return (List<Book>) bookRepository.findAll();
    }

    public void saveBook(Book book){

        bookRepository.save(book);
    }

    public Book updateBook(Long id) throws BookException {

        Optional<Book> optionalGetBook = bookRepository.findById(id);

        if(optionalGetBook.isPresent()){
            return optionalGetBook.get();
        }
        throw new BookException("No any book with ID: " + id);
    }

    public void deleteBook(Long id){

        bookRepository.deleteById(id);
    }
}
