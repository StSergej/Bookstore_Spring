package com.example.db_bookstore.entityControllerTest;

import com.example.db_bookstore.controller.MainController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showCreateMockMvc(){
        assertNotNull(mockMvc);
        System.out.println("Hello, MockMvc!");
    }

    @Test
    public void indexPageHtmlForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(""))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
