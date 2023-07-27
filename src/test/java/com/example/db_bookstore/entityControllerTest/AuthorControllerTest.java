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

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_showAuthorList() throws Exception {

        List<Author> listAuthors = new ArrayList<>();

        listAuthors.add(new Author( 1L, "Paulo Coelho", "Brazilian","Brazilian author..."));
        listAuthors.add(new Author( 2L, "Charles Dickens", "British","English novelist..."));

        Mockito.when(authorService.listAllAuthor()).thenReturn(listAuthors);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/authors");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void test_showNewAuthor() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/authors/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("newAuthor"))
                .andExpect(model().attribute("author", instanceOf(Author.class)))
                .andDo(print());
    }

    @Test
    public void test_saveNewAuthor() throws Exception {

        Author saveNewAuthor = new Author();

        saveNewAuthor.setId(3L);
        saveNewAuthor.setAuthorName("Clive Levis");
        saveNewAuthor.setNationality("British");
        saveNewAuthor.setAnnotation("British writer, literary scholar, and Anglican lay theologian.");

        Mockito.doNothing().when(authorService).saveAuthor(saveNewAuthor);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/authors/save"))
                .andExpect(redirectedUrl("/authors"))
                .andExpect(status().is3xxRedirection())//MockHttpServletResponse:Status = 302
//                .andExpect(status().isOk())
                .andDo(print());

//        Mockito.verify(authorService, times(1)).saveAuthor(saveNewAuthor);

    }

    @Test
    public void test_showEditAuthor() throws AuthorException, Exception {

        Long changeAuthorById = 9L;

        Author changedAuthor = new Author
                ( 9L,"Paulo Coelho", "Brazilian",
                        "Brazilian author known for 'The Alchemist' and other inspirational works.");

        Mockito.when(authorService.updateAuthor(changeAuthorById)).thenReturn(changedAuthor);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/authors/edit/" + changeAuthorById))
                .andExpect(view().name("newAuthor"))
                .andExpect(status().isOk())
                .andDo(print());

        verify(authorService, times(1)).updateAuthor(9L);
    }

    @Test
    public void test_deleteAuthorById() throws Exception {

        Author author = new Author();

        author.setId(3L);
        author.setAuthorName("Clive Levis");
        author.setNationality("British");
        author.setAnnotation("British writer, literary scholar, and Anglican lay theologian.");

        Mockito.doNothing().when(authorService).deleteAuthor(author.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/authors/delete/" + author.getId()))
                .andExpect(redirectedUrl("/authors"))
                .andExpect(status().is3xxRedirection())//MockHttpServletResponse:Status = 302
                .andDo(print());
    }

}
