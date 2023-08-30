package ru.rrenat358.stats.services;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.rrenat358.stats.exceptions.CoreServiceIntegrationException;
import ru.rrenat358.stats.integrations.CartServiceIntegration;
import ru.rrenat358.stats.integrations.CoreServiceIntegration;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class OrdersStatsServiceTest {

    @Autowired
    private OrdersStatsService ordersStatsService;

    @MockBean
    private CoreServiceIntegration coreServiceIntegrationMock;






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

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameExists_not0() {
        String username = "Ivan";

        Mockito.when(coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser("Ivan"))
                .thenReturn(5);
        Integer result = coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser(username);
        Assertions.assertEquals(5,result);
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