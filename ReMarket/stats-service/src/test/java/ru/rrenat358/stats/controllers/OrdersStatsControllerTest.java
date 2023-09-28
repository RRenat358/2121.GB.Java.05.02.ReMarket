package ru.rrenat358.stats.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.stats.services.OrdersStatsService;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrdersStatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrdersStatsService ordersStatsService;

    private static final String BASE_PATH = "/api/v1/orders-stats";

/*
    https://www.baeldung.com/integration-testing-in-spring
    https://www.codejava.net/frameworks/spring-boot/unit-testing-rest-apis-tutorial
    https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/
    https://stackoverflow.com/questions/18336277/how-to-check-string-in-response-body-with-mockmvc

*/


    //============================================================
    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameNull_0() throws Exception {
        String username = null;

        Mockito.when(ordersStatsService.getNumberOfOrdersByCurrentUser(username))
                .thenReturn(0);

        mockMvc.perform(get(BASE_PATH + "/number-of-orders-by-user")
//                        .header("username", null)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", is(0)))
//                .andDo(print())
        ;
    }

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameEmpty_0() throws Exception {
        String username = "";

        Mockito.when(ordersStatsService.getNumberOfOrdersByCurrentUser(username))
                .thenReturn(0);

        mockMvc.perform(get(BASE_PATH + "/number-of-orders-by-user")
                        .header("username", ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", is(0)))
//                .andDo(print())
        ;
    }

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameExists_not0() throws Exception {
        Integer numberOfOrders = 5;
        String username = "Ivan";

        Mockito.when(ordersStatsService.getNumberOfOrdersByCurrentUser(username))
                .thenReturn(numberOfOrders);

        mockMvc.perform(get(BASE_PATH + "/number-of-orders-by-user")
                        .header("username", username))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", is(5)))
                .andDo(print())
        ;
    }


    //============================================================
    @Test
    void getAllOrdersByCurrentUser_isUsernameNull_0() throws Exception {
        String username = null;
        OrderDto orderDtoList = new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null);

        Mockito.when(ordersStatsService.getAllOrdersByCurrentUser(username))
                .thenReturn(Optional.of(orderDtoList));

        mockMvc.perform(get(BASE_PATH + "/all-orders")
//                        .header("username", null)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username").value("[username]"))
                .andDo(print());
    }


    @Test
    void getAllOrdersByCurrentUser_isUsernameEmpty_0() throws Exception {
        String username = "";
        OrderDto expectedOrderDtoList = new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null);

        Mockito.when(ordersStatsService.getAllOrdersByCurrentUser(username))
                .thenReturn(Optional.of(expectedOrderDtoList));

        mockMvc.perform(get(BASE_PATH + "/all-orders")
                        .header("username", "")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username").value("[username]"))
                .andDo(print());
    }


    @Test
    void getAllOrdersByCurrentUser_isUsernameExists_not0() throws Exception {
        String username = "Ivan";
        OrderDto expectedOrderDtoList = new OrderDto(3L, username, BigDecimal.valueOf(100), "Voronezh", "+79670670112", null);

        Mockito.when(ordersStatsService.getAllOrdersByCurrentUser(username))
                .thenReturn(Optional.of(expectedOrderDtoList));

        mockMvc.perform(get(BASE_PATH + "/all-orders")
                        .header("username", username)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username").value(username))
                .andDo(print());
    }


    //============================================================

    @Test
    void topProductsByAllCarts() {
    }


    //============================================================

    @Test
    void topProductsByAllOrders() {
    }


}