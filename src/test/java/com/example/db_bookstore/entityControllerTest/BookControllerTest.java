package com.example.db_bookstore.entityControllerTest;

import com.example.db_bookstore.controller.BookController;
import com.example.db_bookstore.entities.Book;
import com.example.db_bookstore.service.BookService;

import com.example.db_bookstore.service.entityException.BookException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_showBookList() throws Exception {

        List<Book> listBooks = new ArrayList<>();

        listBooks.add(new Book
                (1L,"The Alchemist", "HarperOne", "9780062315007", 208L, "25.99"));
        listBooks.add(new Book
                (2L,"David Copperfield","HarperCollins Publishers", "9780004244716", 831L,"29.99"));

        Mockito.when(bookService.listAllBook()).thenReturn(listBooks);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void test_saveNewBook() throws Exception {

        Book newBook = new Book();

        newBook.setId(15L);
        newBook.setBookName("The Chronicles of Narnia");
        newBook.setPublisher("Collier-Macmillan");
        newBook.setIsbn("9780062690579");
        newBook.setTotalPages(912L);
        newBook.setPrice("39.99");

        Mockito.doNothing().when(bookService).saveBook(newBook);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books/save");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
    }
    @Test
    public void test_showEditBook() throws Exception, BookException {

        Long changeBookById = 2L;
        Book changeBook = new Book
                (2L,"David Copperfield","HarperCollins Publishers", "9780004244716", 831L,"19.99");

        Mockito.when(bookService.updateBook(changeBookById)).thenReturn(changeBook);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/edit/" + changeBookById);

        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void test_deleteBookById() throws Exception {

        Long bookId = 11L;

        Mockito.doNothing().when(bookService).deleteBook(bookId);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/delete/" + bookId);

        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());

        Mockito.verify(bookService, times(1)).deleteBook(bookId);
    }

}
