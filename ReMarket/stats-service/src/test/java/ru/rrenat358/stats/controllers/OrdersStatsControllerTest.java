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
    https://www.codejava.net/frameworks/spring-boot/unit-testing-rest-apis-tutorial
    https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/
*/


    //============================================================
    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameNull_0() throws Exception {
        String username = null;

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
    void getNumberOfOrdersByCurrentUser_isUsernameEmpty_0() throws Exception {
        String username = "";

        Mockito.when(ordersStatsService.getNumberOfOrdersByCurrentUser(username))
                .thenReturn(0);

        mockMvc.perform(get(BASE_PATH + "/number-of-orders-by-user")
                        .header("username", ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", is(0)))
                .andDo(print())
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
//                .andDo(print())
        ;
    }



    //============================================================
/*
    @Test
    void getAllOrdersByCurrentUser() {
    }
*/
    @Test
    void getAllOrdersByCurrentUser_isUsernameNull_0() throws Exception {
        String username = null;
        OrderDto orderDtoList = new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null);

        Mockito.when(ordersStatsService.getAllOrdersByCurrentUser(username))
                .thenReturn(Optional.of(orderDtoList));

        mockMvc.perform(get(BASE_PATH + "/all-orders")
                        .header("username", ""))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", is(orderDtoList)))
                .andDo(print())
        ;
    }

//    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());


    @Test
    void getAllOrdersByCurrentUser_isUsernameNull_0_2() throws Exception {
        String username = null;
        OrderDto orderDtoList = new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null);

        System.out.println(orderDtoList.getId());
        System.out.println(orderDtoList.getUsername());
        System.out.println(orderDtoList.getTotalPrice());


//        OrderDto expected = new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null);
//        Optional<OrderDto> actual = ordersStatsService.getAllOrdersByCurrentUser(username);

        Mockito.when(ordersStatsService.getAllOrdersByCurrentUser(username))
                .thenReturn(Optional.of(orderDtoList));
/*
        Optional<OrderDto> orderDtoList2 = Mockito.when(ordersStatsService.getAllOrdersByCurrentUser(username))
                .thenReturn(orderDtoList);
*/

// https://stackoverflow.com/questions/18336277/how-to-check-string-in-response-body-with-mockmvc
/*
        MvcResult result = mockMvc.perform(get(BASE_PATH + "/all-orders").header("username", "").contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(orderDtoList)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
*/


        mockMvc.perform(get(BASE_PATH + "/all-orders")
                        .header("username", ""))

                .andExpect(status().isOk())

//                .andExpect(content().contentType("application/json"))
//                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.data.id", is(0L)))
                .andExpect(content().json("{'id':'0L'}"))
//                .andExpect(jsonPath("$['id']", is(0L)))
//                .andExpect(jsonPath("[0].id", is(0L)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0L))
//                .andExpect(jsonPath("$.username", is("[username]")))
                .andDo(print())
                .andReturn().getResponse().getContentAsString()

        ;






    }

    @Test
    void getAllOrdersByCurrentUser_isUsernameEmpty_0() {


    }

    @Test
    void getAllOrdersByCurrentUser_isUsernameExists_not0() {


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