package ru.rrenat358.stats.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersStatsServiceTest {


    @Autowired
    private OrdersStatsService ordersStatsService;


    @Test
    void getNumberOfOrdersByCurrentUser() {

/*
        // Given
        WindowControl control = new WindowControl("My AFrame");
        AFrame frame = new AFrame();

        // When
        control.closeWindow();

        // Then
        ensureThat(!frame.isShowing());
*/


    }

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameNull_0() {
        String usernameNull = null;
        Integer r = ordersStatsService.getNumberOfOrdersByCurrentUser(usernameNull);
        assertEquals(0,r);
    }


    String usernameIsBlank = " ";













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