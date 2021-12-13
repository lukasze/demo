package com.example.nowedemo.controller;

import com.example.nowedemo.model.Car;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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
class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("HTTP /cars -> 200 OK")
    void listCars() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/cars").accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("HTTP /car -> 404 OK")
    void listCar_returns404() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/car").accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /*@Test
    @DisplayName("HTTP POST /cars -> 405 MNA")
    void listCarsPOST_returns405() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars").accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
    }*/

    @Test
    @DisplayName("HTTP /cars -> 200 OK; 4 cars")
    void listCars_returns4Cars() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/cars")
                ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        List<Car> cars = objectMapper.readValue(json, new TypeReference<>() {
        });

        Assertions.assertEquals(4, cars.size());
    }

    @Test
    @DisplayName("HTTP POST /cars -> 200 OK")
    void addCar_returnCar() throws Exception {

        Car testCar = new Car("TestModel", "TestMarka");

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/cars").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testCar))
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        Car car = objectMapper.readValue(json, Car.class);

        Assertions.assertNotNull(car);
        Assertions.assertEquals("TestModel", car.getModel());
        Assertions.assertEquals("TestMarka", car.getMarka());
    }
}