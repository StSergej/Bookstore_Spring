package com.example.db_bookstore.controller;

import com.example.db_bookstore.entities.Book;
import com.example.db_bookstore.service.BookService;
import com.example.db_bookstore.service.entityException.BookException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/books")
    public String showBookList(Model model) {

        List<Book> listBooks = bookService.listAllBook();
        model.addAttribute("listBooks", listBooks);

        return "books";
    }

    @GetMapping("/books/new")
    public String showNewBook(Model model) {

        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add New Book");

        return "newBook";
    }

    @PostMapping("books/save")
    public String saveNewBook(Book book) {

        bookService.saveBook(book);

        return "redirect:/books";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/books/edit/{id}")
    public String showEditBook(@PathVariable("id") Long id, Model model){

        try {
            Book book = bookService.updateBook(id);
            model.addAttribute("book", book);
            model.addAttribute("pageTitle", "Edit Book(ID: " + id + ")");

            return "newBook";

        } catch (BookException e) {
            e.printStackTrace();
        }
        return "redirect:/books";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/books/delete/{id}")
    public String deleteBookById(@PathVariable("id") Long id){

        bookService.deleteBook(id);

        return "redirect:/books";
    }

}

