package com.example.db_bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name", nullable = false, length = 45)
    private String authorName;

    @Column(length = 25)
    private String nationality;

    @Column(nullable = false, length = 150)
    private String annotation;


    @OneToMany(mappedBy = "author")
    private List<Book> books;


    public Author() {
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(Long id, String authorName) {
        this.id = id;
        this.authorName = authorName;
    }

    public Author(String authorName, String nationality, String annotation) {
        this.authorName = authorName;
        this.nationality = nationality;
        this.annotation = annotation;
    }

    public Author(Long id, String authorName, String nationality, String annotation) {
        this.id = id;
        this.authorName = authorName;
        this.nationality = nationality;
        this.annotation = annotation;
    }



    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", annotation='" + annotation + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(authorName, author.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorName);
    }
}
