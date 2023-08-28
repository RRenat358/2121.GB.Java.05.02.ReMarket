package ru.rrenat358.stats.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import ru.rrenat358.stats.integrations.CartServiceIntegration;
import ru.rrenat358.stats.integrations.CoreServiceIntegration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class OrdersStatsServiceTest {
    @Autowired
    private OrdersStatsService ordersStatsService;

//    @Mock
//    private CoreServiceIntegration coreServiceIntegrationMock = Mockito.mock(CoreServiceIntegration.class);

/*
    @BeforeEach
    public void setup() {
        Mockito.when(coreServiceIntegration.getNumberOfOrdersByCurrentUser("Ivan")).thenReturn(5);
    }
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
    void getNumberOfOrdersByCurrentUser_isUsernameExists_not0(/*@Mock CoreServiceIntegration coreServiceIntegration*/) {
        String username = "Ivan";
        CoreServiceIntegration coreServiceIntegrationMock = Mockito.mock(CoreServiceIntegration.class);
//        private OrdersStatsService ordersStatsService;
        WebClient coreServiceWebClient = Mockito.mock(WebClient.class);
        Mockito.when(coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser("Ivan")).thenReturn(5);
//        when(coreServiceIntegration.getNumberOfOrdersByCurrentUser(username)).thenReturn(5);
        Integer r = ordersStatsService.getNumberOfOrdersByCurrentUser(username);
        Assertions.assertEquals(5,r);
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