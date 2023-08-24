package ru.rrenat358.stats.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rrenat358.stats.integrations.CartServiceIntegration;
import ru.rrenat358.stats.integrations.CoreServiceIntegration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class OrdersStatsServiceTest {


    @Autowired
    private OrdersStatsService ordersStatsService;

/*
    @Autowired
    @Mock
    private CoreServiceIntegration coreServiceIntegration;
*/

/*
    @BeforeEach
    void init(@Mock CoreServiceIntegration coreServiceIntegration) {
//        userService = new DefaultUserService(userRepository, settingRepository, mailClient);

//        CoreServiceIntegration coreServiceIntegration = new CoreServiceIntegration();


        when(coreServiceIntegration.getNumberOfOrdersByCurrentUser("username")).thenReturn(5);

    }

*/


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

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameEmpty_0() {
        String usernameEmpty = "";
        Integer r = ordersStatsService.getNumberOfOrdersByCurrentUser(usernameEmpty);
        assertEquals(0,r);
    }


//    coreServiceIntegration.getNumberOfOrdersByCurrentUser(username)
    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameExists_not0(@Mock CoreServiceIntegration coreServiceIntegration) {
        String username = "Ivan";

        when(coreServiceIntegration.getNumberOfOrdersByCurrentUser(username)).thenReturn(5);
        Integer r = ordersStatsService.getNumberOfOrdersByCurrentUser(username);
        assertEquals(5,r);
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