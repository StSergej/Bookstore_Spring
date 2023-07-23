package com.example.db_bookstore.controller;

import com.example.db_bookstore.entities.Author;
import com.example.db_bookstore.service.AuthorService;

import com.example.db_bookstore.service.entityException.AuthorException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public String showAuthorList(Model model){

        List<Author> listAuthors = authorService.listAllAuthor();
        model.addAttribute("listAuthors", listAuthors);

        return "authors";
    }

    @GetMapping("/authors/new")
    public String showNewAuthor(Model model){

        model.addAttribute("author", new Author());
        model.addAttribute("pageTitle", "Add New Author");

        return "newAuthor";
    }

    @PostMapping("/authors/save")
    public String saveNewAuthor(Author author){

        authorService.saveAuthor(author);

        return "redirect:/authors";
    }

    @GetMapping("/authors/edit/{id}")
    public String showEditAuthor(@PathVariable("id") Long id, Model model){

        try {
            Author author = authorService.updateAuthor(id);

            model.addAttribute("author", author);
            model.addAttribute("pageTitle", "Edit Author(ID: " + id + ")");

            return "newAuthor";

        } catch (AuthorException e) {
            e.printStackTrace();
        }
        return "redirect:/authors";
    }


    @GetMapping("/authors/delete/{id}")
    public String deleteAuthorById(@PathVariable("id") Long id){

        authorService.deleteAuthor(id);

        return "redirect:/authors";

    }

}
