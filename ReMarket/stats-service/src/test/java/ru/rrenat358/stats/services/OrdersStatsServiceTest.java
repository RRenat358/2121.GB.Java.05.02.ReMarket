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
import org.springframework.http.HttpStatus;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class OrdersStatsServiceTest {

    @Autowired
    private OrdersStatsService ordersStatsService;

    @Mock
    private CoreServiceIntegration coreServiceIntegrationMock = Mockito.mock(CoreServiceIntegration.class);

    @Mock
    private WebClient webClientMock = Mockito.mock(WebClient.class);

/*
    @Mock
    private WebClient webClientMock;

    @InjectMocks
    private CoreServiceIntegration coreServiceIntegrationMock = new CoreServiceIntegration(webClientMock);

    public static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s",
                mockBackEnd.getPort());
        coreServiceIntegrationMock = new CoreServiceIntegration(
                (WebClient) webClientMock.get()
                .uri(baseUrl));
    }
 */



    private WebClient webClientMock;

//    Map<String, String> result = myHttpService.callService().block();

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameExists_not0() {
        String username = "Ivan";
        webClientMock = WebClient.builder()
                .exchangeFunction(clientRequest ->
                        Mono.just(ClientResponse.create(HttpStatus.OK)
                                .header("content-type", "application/json")
                                .body("{ \"key\" : \"value\"}")
                                .build())
                ).build();
        CoreServiceIntegration coreServiceIntegrationMock = new CoreServiceIntegration(webClientMock);
        Map<String, String> result = coreServiceIntegrationMock.callService().block();
/*
        Mockito.when(coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser("Ivan"))
                .thenReturn(5);
*/
        Integer r = coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser(username);
        Assertions.assertEquals(5,r);
    }

    @Mock
    private ExchangeFunction exchangeFunction;

    @BeforeEach
    void init() {
        WebClient webClientMock = WebClient.builder()
                .exchangeFunction(exchangeFunction)
                .build();

        CoreServiceIntegration coreServiceIntegrationMock = new CoreServiceIntegration(webClientMock);
    }

    @Test
    void callService() {
        when(exchangeFunction.exchange(any(ClientRequest.class)))
                .thenReturn(buildMockResponse());
        Map<String, String> result = coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser().block();

        verify(exchangeFunction).exchange(any());

        // Do assertions here
    }















/*
    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameExists_not0() {
        String username = "Ivan";

        Mockito.when(coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser("Ivan"))
                .thenReturn(5);
        Integer r = coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser(username);
        Assertions.assertEquals(5,r);
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