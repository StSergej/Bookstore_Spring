package com.example.db_bookstore.entityControllerTest;

import com.example.db_bookstore.controller.BookController;
import com.example.db_bookstore.entities.Author;
import com.example.db_bookstore.entities.Book;
import com.example.db_bookstore.service.BookService;
import com.example.db_bookstore.service.entityException.BookException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                (1L,"The Alchemist", "HarperOne", "9780062315007",
                        208L, "25.99", new Author(4L)));
        listBooks.add(new Book
                (2L,"David Copperfield","HarperCollins Publishers",
                        "9780004244716", 831L,"29.99", new Author(5L)));

        Mockito.when(bookService.listAllBook()).thenReturn(listBooks);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void test_showNewBook() throws Exception {

        Author author = new Author(5L);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("newBook"))
                .andExpect(model().attribute("book", instanceOf(Book.class)))
                .andDo(print());
    }


    @Test
    public void test_saveNewBook() throws Exception {

        Author author = new Author(7L);
        Book saveBook = new Book();

        saveBook.setId(15L);
        saveBook.setBookName("The Chronicles of Narnia");
        saveBook.setPublisher("Collier-Macmillan");
        saveBook.setIsbn("9780062690579");
        saveBook.setTotalPages(912L);
        saveBook.setPrice("39.99");
        saveBook.setAuthor(author);

        Mockito.doNothing().when(bookService).saveBook(saveBook);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/save"))
                .andExpect(redirectedUrl("/books"))
                .andExpect(status().is3xxRedirection())//MockHttpServletResponse:Status = 302
                .andDo(print());
    }
    @Test
    public void test_showEditBook() throws Exception, BookException {

        Long changeBookById = 2L;

        Book changeBook = new Book
                (2L,"David Copperfield","HarperCollins Publishers",
                        "9780004244716", 831L,"19.99", new Author(12L));

        Mockito.when(bookService.updateBook(changeBookById)).thenReturn(changeBook);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/edit/" + changeBookById))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void test_deleteBookById() throws Exception {

        Long bookId = 9L;

        Mockito.doNothing().when(bookService).deleteBook(bookId);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/delete/" + bookId))
                .andExpect(redirectedUrl("/books"))
                .andExpect(status().is3xxRedirection())//MockHttpServletResponse:Status = 302
                .andDo(print());

        Mockito.verify(bookService, times(1)).deleteBook(bookId);
    }

}
