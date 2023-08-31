package ru.rrenat358.stats.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.core.ProductTopInCartDto;
import ru.rrenat358.api.core.ProductTopInOrdersDto;
import ru.rrenat358.stats.integrations.CartServiceIntegration;
import ru.rrenat358.stats.integrations.CoreServiceIntegration;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrdersStatsServiceTest {

    @Autowired
    private OrdersStatsService ordersStatsService;

    @MockBean
    private CoreServiceIntegration coreServiceIntegrationMock;

    @MockBean
    private CartServiceIntegration cartServiceIntegrationMock;

    //============================================================
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

    //============================================================
    @Test
    void getAllOrdersByCurrentUser_isUsernameNull_0() {
        String usernameNull = null;
        OrderDto expected = new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null);
        Optional<OrderDto> actual = ordersStatsService.getAllOrdersByCurrentUser(usernameNull);
        Assertions.assertEquals(expected.getId(), actual.get().getId());
        Assertions.assertEquals(expected.getUsername(), actual.get().getUsername());
    }

    @Test
    void getAllOrdersByCurrentUser_isUsernameEmpty_0() {
        String usernameEmpty = "";
        Optional<OrderDto> expected = Optional.ofNullable(
                new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null));
        Optional<OrderDto> actual = ordersStatsService.getAllOrdersByCurrentUser(usernameEmpty);
        Assertions.assertEquals(expected.get().getId(), actual.get().getId());
        Assertions.assertEquals(expected.get().getUsername(), actual.get().getUsername());
    }

    @Test
    void getAllOrdersByCurrentUser_isUsernameExists_0() {
        String username = "Ivan";
        Optional<OrderDto> expected = Optional.ofNullable(
                new OrderDto(555L, "Ivan", BigDecimal.valueOf(0), "0", "0", null));
        Mockito.when(coreServiceIntegrationMock.getAllOrdersByCurrentUser("Ivan"))
                .thenReturn(expected);
        Optional<OrderDto> actual = ordersStatsService.getAllOrdersByCurrentUser(username);
        Assertions.assertEquals(expected.get().getId(), actual.get().getId());
        Assertions.assertEquals(expected.get().getUsername(), actual.get().getUsername());
    }

    //============================================================
    @Test
    void topProductsByAllCarts() {
        Optional<ProductTopInCartDto> expected = Optional.of(
                new ProductTopInCartDto(3L, "Банан", BigDecimal.valueOf(50.30), 8));
        Mockito.when(cartServiceIntegrationMock.topProductsByAllCarts(2))
                .thenReturn(expected);
        Optional<ProductTopInCartDto> actual = ordersStatsService.topProductsByAllCarts(2);
        Assertions.assertEquals(expected.get().getId(), actual.get().getId());
        Assertions.assertEquals(expected.get().getTitle(), actual.get().getTitle());
    }

    //============================================================
    @Test
    void topProductsByAllOrders() {
        Optional<ProductTopInOrdersDto> expected = Optional.of(
                new ProductTopInOrdersDto(3L, "Банан", BigDecimal.valueOf(50.30), "Фрукты", 8));
        Mockito.when(coreServiceIntegrationMock.topProductsByAllOrders(2))
                .thenReturn(expected);
        Optional<ProductTopInOrdersDto> actual = ordersStatsService.topProductsByAllOrders(2);
        Assertions.assertEquals(expected.get().getId(), actual.get().getId());
        Assertions.assertEquals(expected.get().getTitle(), actual.get().getTitle());
    }


}