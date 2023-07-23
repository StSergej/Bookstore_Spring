package com.example.db_bookstore.entityRepositoryTest;

import com.example.db_bookstore.entities.Author;
import com.example.db_bookstore.repository.AuthorRepository;
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
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testAddNewAuthor(){

        Author author = new Author();

        author.setAuthorName("Paulo Coelho");
        author.setNationality("Brazilian");
        author.setAnnotation("Brazilian author known for 'The Alchemist' and other inspirational works.");

        author.setAuthorName("Charles Dickens");
        author.setNationality("British");
        author.setAnnotation("English novelist, generally considered the greatest of the Victorian era.");


        Author savedAuthor = authorRepository.save(author);

        Assertions.assertThat(savedAuthor).isNotNull();
        Assertions.assertThat(savedAuthor.getId()).isGreaterThan(0);

        System.out.println(savedAuthor);
    }

    @Test
    public void testAllAuthor(){
        Iterable<Author> authors = authorRepository.findAll();

        Assertions.assertThat(authors).hasSizeGreaterThan(0);

        for(Author allAuthors : authors){
            System.out.println(allAuthors);
        }

    }

    @Test
    public void testUpdateAuthor(){
        Long authorId = 9L;

        Optional<Author> optionalAuthor = authorRepository.findById(authorId);

        Author author = optionalAuthor.get();

        author.setAnnotation("Brazilian author");

        authorRepository.save(author);

        Author updatedAuthor = authorRepository.findById(authorId).get();

        Assertions.assertThat(updatedAuthor.getAnnotation())
                .isEqualTo("Brazilian author");

        System.out.println(updatedAuthor);

    }

    @Test
    public void testGetAuthor(){
        Long authorId = 5L;

        Optional<Author> optionalGetAuthor = authorRepository.findById(authorId);

        Assertions.assertThat(optionalGetAuthor).isPresent();

        System.out.println(optionalGetAuthor.get());
    }

    @Test
    public void testDeleteAuthor(){
        Long authorId = 9L;

        authorRepository.deleteById(authorId);

        Optional<Author> optionalDeleteAuthor = authorRepository.findById(authorId);

        Assertions.assertThat(optionalDeleteAuthor).isNotPresent();

    }

}