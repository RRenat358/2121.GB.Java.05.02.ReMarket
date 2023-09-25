package ru.rrenat358.stats.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrdersStatsControllerTest {

    @Autowired
    private MockMvc mockMvc;


/*
    https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/
*/

/*
    @Test
    void getNumberOfOrdersByCurrentUser() {
    }
*/

    @Test
    void getNumberOfOrdersByCurrentUser() throws Exception {
        Integer NumberOfOrders = 5;

        mockMvc.perform(get("api/v1/orders-stats/number-of-orders-by-user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());


//        Assertions.assertEquals(5, );
    }


    @Test
    void getAllOrdersByCurrentUser() {
    }

    @Test
    void topProductsByAllCarts() {
    }

    @Test
    void topProductsByAllOrders() {
    }
}