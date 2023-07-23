package com.example.db_bookstore.entityRepositoryTest;

import com.example.db_bookstore.entities.Author;
import com.example.db_bookstore.entities.Book;
import com.example.db_bookstore.repository.AuthorRepository;
import com.example.db_bookstore.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    public void testAddNewBook(){
        Long authorId = 9L;

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        Author author = optionalAuthor.get();

        Book book = new Book();

        book.setBookName("The Alchemist");
        book.setAuthor(author);
        book.setPublisher("HarperOne");
        book.setIsbn("9780062315007");
        book.setTotalPages(208L);
        book.setPrice("25,99");

        Book savedBook = bookRepository.save(book);

        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getId()).isGreaterThan(0);

        System.out.println(savedBook);
    }

    @Test
    public void testAllBook(){

        Iterable<Book> books = bookRepository.findAll();

        Assertions.assertThat(books).hasSizeGreaterThan(0);
        for(Book allBooks : books){
            System.out.println(allBooks);
        }
    }

    @Test
    public void testUpdateBook(){
        Long bookId = 11L;

        Optional<Book> optionalUpdatedBook = bookRepository.findById(bookId);

        Book book = optionalUpdatedBook.get();
        book.setPrice("12,99"); // price = 25,99

        bookRepository.save(book);

        Book updatedBook = bookRepository.findById(bookId).get();

        Assertions.assertThat(updatedBook.getPrice()).isEqualTo("12,99");

        System.out.println(updatedBook);
    }

    @Test
    public void testGetBook(){
        Long bookId = 9L;

        Optional<Book> optionalGetBook = bookRepository.findById(bookId);

        Assertions.assertThat(optionalGetBook).isPresent();

        System.out.println(optionalGetBook.get());
    }

    @Test
    public void testDeleteBook(){

        Long bookId = 11L;

        bookRepository.deleteById(bookId);

        Optional<Book> optionalDeleteBook = bookRepository.findById(bookId);

        Assertions.assertThat(optionalDeleteBook).isNotPresent();
    }

}
