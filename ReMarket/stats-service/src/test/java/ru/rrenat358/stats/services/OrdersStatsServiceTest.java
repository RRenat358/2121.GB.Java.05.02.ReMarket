package ru.rrenat358.stats.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.stats.integrations.CartServiceIntegration;
import ru.rrenat358.stats.integrations.CoreServiceIntegration;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
class OrdersStatsServiceTest {

    @Autowired
    private OrdersStatsService ordersStatsService;

    @MockBean
    private CoreServiceIntegration coreServiceIntegrationMock;

    @MockBean
    private CartServiceIntegration cartServiceIntegrationMock;

/*
    @Before
    public void setup() {
        DatabindCodec.mapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }
*/

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameNull_0() {
        String usernameNull = null;
        Integer r = ordersStatsService.getNumberOfOrdersByCurrentUser(usernameNull);
        Assertions.assertEquals(0, r);
    }

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameEmpty_0() {
        String usernameEmpty = "";
        Integer r = ordersStatsService.getNumberOfOrdersByCurrentUser(usernameEmpty);
        Assertions.assertEquals(0, r);
    }

    @Test
    void getNumberOfOrdersByCurrentUser_isUsernameExists_not0() {
        String username = "Ivan";
        Mockito.when(coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser("Ivan"))
                .thenReturn(5);
        Integer result = coreServiceIntegrationMock.getNumberOfOrdersByCurrentUser(username);
        Assertions.assertEquals(5, result);
    }


    @Test
    void getAllOrdersByCurrentUser_isUsernameNull_0() {
        String usernameNull = null;
        OrderDto expected = new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null);
        Optional<OrderDto> actual = ordersStatsService.getAllOrdersByCurrentUser(usernameNull);
        Assertions.assertEquals(expected.getId(), actual.get().getId());
        Assertions.assertEquals(expected.getUsername(), actual.get().getUsername());
    }

/*
    @Test
    void getAllOrdersByCurrentUser_isUsernameNull_02() throws JSONException, JsonProcessingException {
        String usernameNull = null;
//        JSONObject expected = new JSONObject ("{\"id\":0, \"username\":\"username\", \"totalPrice\":0, \"address\":\"0\", \"phone\":\"0\", \"itemList\":null}");
        JSONObject expected = new JSONObject ("{id:0, username:\"username\", totalPrice:0, address:\"0\", phone:\"0\", itemList:null}");
        Optional<OrderDto> actual = ordersStatsService.getAllOrdersByCurrentUser(usernameNull);

//        ObjectMapper mapper = new ObjectMapper();
//        JSONObject actual2 = new JSONObject (mapper.writeValueAsString(actual));

//        Assertions.assertEquals(expected.toString(), actual.toString());
//        Assertions.assertEquals(expected.toString(), mapper.writeValueAsString(actual));
//        Assertions.assertEquals(expected.getUsername(), actual.get().getUsername());
//        JSONAssert.assertEquals(expected, actual,false);

        Optional<OrderDto> data = actual;
        JsonObject object = Json.parse(input).asObject();
        String expected2 = "{id:0, username:\"username\", totalPrice:0, address:\"0\", phone:\"0\", itemList:null}";
        JSONAssert.assertEquals(expected2, String.valueOf(data), false);

    }
*/

    @Test
    void getAllOrdersByCurrentUser_isUsernameEmpty_0() {
        String usernameEmpty = "";
        Optional<OrderDto> expected = Optional.ofNullable(new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null));
        Optional<OrderDto> actual = ordersStatsService.getAllOrdersByCurrentUser(usernameEmpty);
        Assertions.assertEquals(expected.get().getId(), actual.get().getId());
        Assertions.assertEquals(expected.get().getUsername(), actual.get().getUsername());
    }

    @Test
    void getAllOrdersByCurrentUser_isUsernameExists_0() {
        String username = "Ivan";
        Optional<OrderDto> expected = Optional.ofNullable(new OrderDto(1L, "Ivan", BigDecimal.valueOf(0), "0", "0", null));
        Mockito.when(coreServiceIntegrationMock.getAllOrdersByCurrentUser("Ivan"))
                .thenReturn(expected);
        Optional<OrderDto> actual = ordersStatsService.getAllOrdersByCurrentUser(username);
        Assertions.assertEquals(expected.get().getId(), actual.get().getId());
        Assertions.assertEquals(expected.get().getUsername(), actual.get().getUsername());

    }


    @Test
    void topProductsByAllCarts() {

        Optional<OrderDto> expected = Optional.ofNullable(new OrderDto(1L, "Ivan", BigDecimal.valueOf(0), "0", "0", null));
        Mockito.when(coreServiceIntegrationMock.getAllOrdersByCurrentUser("Ivan"))
                .thenReturn(expected);




    }








    @Test
    void topProductsByAllOrders() {
    }


}