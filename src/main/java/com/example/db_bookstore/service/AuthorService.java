package com.example.db_bookstore.service;

import com.example.db_bookstore.entities.Author;
import com.example.db_bookstore.repository.AuthorRepository;
import com.example.db_bookstore.service.entityException.AuthorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> listAllAuthor(){

        return (List<Author>) authorRepository.findAll();
    }

    public void saveAuthor(Author author){

        authorRepository.save(author);
    }

    public Author updateAuthor(Long id) throws AuthorException {

        Optional<Author> optionalGetAuthor = authorRepository.findById(id);

        if(optionalGetAuthor.isPresent()){
            return optionalGetAuthor.get();
        }
        throw new AuthorException("No any Author with ID: " + id);
    }

    public void deleteAuthor(Long id){

        authorRepository.deleteById(id);
    }
}
