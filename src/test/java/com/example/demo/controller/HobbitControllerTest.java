package com.example.demo.controller;

import com.example.demo.model.Hobbit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class HobbitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("HTTP GET /hobbits -> HTTP 200")
    void hobbitsShouldReturn200() throws Exception {
        mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/hobbits")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("HTTP GET /hobbits -> HTTP 200; 2 hobbits")
    void hobbitsShouldReturn200And2Hobbits() throws Exception {
        /*
            - wywołaj endpoint /hobbit
            - sprawdź status HTTP
            - utwórz z odpowiedzi obiekt MvcResult
         */
        var mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .get("/hobbits")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // wyciągnij z MvcResult JSON'a
        String json = mvcResult.getResponse().getContentAsString();
        // zmapuj JSONa do List<Hobbit>
        List<Hobbit> hobbitsFromDb = objectMapper.readValue(json, new TypeReference<>() {});
        // asercje na obiektach utworzonych z JSON'a
        assertEquals(2, hobbitsFromDb.size());
    }

}