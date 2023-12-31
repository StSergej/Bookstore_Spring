package com.example.db_bookstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name", nullable = false, length = 45)
    private String bookName;

    @Column(name = "publisher_name", nullable = false, length = 45)
    private String publisher;

    @Column(nullable = false, length = 15)
    private String isbn;

    @Column(name = "total_pages", length = 15)
    private Long totalPages;

    @Column(nullable = false, length = 7)
    private String price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<OrderItem> orderItem;


    public Book() {}

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public Book(Long id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    public Book(String bookName, String isbn) {
        this.bookName = bookName;
        this.isbn = isbn;
    }

    public Book(String bookName, String publisher, String price) {
        this.bookName = bookName;
        this.publisher = publisher;
        this.price = price;
    }

    public Book(Long id, String bookName, String publisher,
                String isbn, Long totalPages, String price) {
        this.id = id;
        this.bookName = bookName;
        this.publisher = publisher;
        this.isbn = isbn;
        this.totalPages = totalPages;
        this.price = price;
    }

    public Book(Long id, String bookName, String publisher,
                String isbn, Long totalPages, String price, Author author) {
        this.id = id;
        this.bookName = bookName;
        this.publisher = publisher;
        this.isbn = isbn;
        this.totalPages = totalPages;
        this.price = price;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn='" + isbn + '\'' +
                ", totalPages=" + totalPages +
                ", price='" + price + '\'' +
                ", author:ID=" + author.getId() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookName, book.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName);
    }
}
