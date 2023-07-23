package com.example.db_bookstore.entityControllerTest;

import com.example.db_bookstore.controller.AuthorController;
import com.example.db_bookstore.entities.Author;
import com.example.db_bookstore.service.AuthorService;

import com.example.db_bookstore.service.entityException.AuthorException;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showCreateMockMvc(){
        assertNotNull(mockMvc);
        System.out.println("Hello, MockMvc!");
    }


    @Test
    public void test_showAuthorList() throws Exception {

        List<Author> listAuthors = new ArrayList<>();

        listAuthors.add(new Author( 1L, "Paulo Coelho", "Brazilian","Brazilian author..."));
        listAuthors.add(new Author( 2L, "Charles Dickens", "British","English novelist..."));

        Mockito.when(authorService.listAllAuthor()).thenReturn(listAuthors);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authors");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void test_saveNewAuthor() throws Exception {

        Author newAuthor = new Author();

        newAuthor.setId(3L);
        newAuthor.setAuthorName("Clive Levis");
        newAuthor.setNationality("British");
        newAuthor.setAnnotation("British writer, literary scholar, and Anglican lay theologian.");

        Mockito.doNothing().when(authorService).saveAuthor(newAuthor); // только для void

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/authors/save");
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void test_showEditAuthor() throws AuthorException, Exception {
        Long changeAuthorById = 9L;
        Author changedAuthor = new Author
                ( 9L,"Paulo Coelho", "Brazilian","Brazilian author known for 'The Alchemist' and other inspirational works.");

        Mockito.when(authorService.updateAuthor(changeAuthorById)).thenReturn(changedAuthor);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authors/edit/" + changeAuthorById);

        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void test_deleteAuthorById() throws Exception {
        Long authorId = 8L;

        Mockito.doNothing().when(authorService).deleteAuthor(authorId);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authors/delete/" + authorId);

        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(print());

        Mockito.verify(authorService, times(1)).deleteAuthor(authorId);
    }

}
