package ru.rrenat358.stats.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.rrenat358.stats.services.OrdersStatsService;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrdersStatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdersStatsService ordersStatsService;

/*
    https://www.codejava.net/frameworks/spring-boot/unit-testing-rest-apis-tutorial
    https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/
*/


    //============================================================
/*
    @Test
    void getNumberOfOrdersByCurrentUser() {
    }
*/

    @Test
    void getNumberOfOrdersByCurrentUser_isNumberOfOrders() throws Exception {
        Integer numberOfOrders = 5;
        String username = "Ivan";

        Mockito.when(ordersStatsService.getNumberOfOrdersByCurrentUser(username))
                .thenReturn(numberOfOrders);

        mockMvc.perform(get("/api/v1/orders-stats/number-of-orders-by-user")
                        .header("username", username))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", is(5)))
                .andDo(print());
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