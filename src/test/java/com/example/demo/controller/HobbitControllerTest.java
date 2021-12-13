package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

// Supermoc - Pełen kontekst Spring'a, użyty do testów
@SpringBootTest
// Supermoc - Spring wie jak skonfifgurować MockMvc
@AutoConfigureMockMvc
class HobbitControllerTest {

    // API do testów endpointów, potrzebna konfiguracja dla Spring'a: @AutoConfigureMockMvc
    @Autowired
    private MockMvc mockMvc;

    // Supermoc JUnit - zmiana w metodę testową
    @Test
    @DisplayName("HTTP GET /hobbits -> HTTP 200")
    void hobbitsShouldReturn200() throws Exception {
        // given: zainicjalizowana baza danych (2 rekordy)

        // when: wywołanie endpointu GET /hobbits
        mockMvc
                // wywołanie endpointu
                .perform(
                        MockMvcRequestBuilders
                                // HTTP GET /hobbits
                                .get("/hobbits")
                                // Nagłówek żądania - JSON
                                .accept(MediaType.APPLICATION_JSON)
                )
                // bardziej szczegółowe logowanie
                .andDo(print())
        // then: oczekujemy HTTP 200
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}