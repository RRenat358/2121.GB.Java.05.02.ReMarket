package ru.rrenat358.stats.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.core.ProductTopInCartDto;
import ru.rrenat358.api.core.ProductTopInOrdersDto;
import ru.rrenat358.stats.integrations.CartServiceIntegration;
import ru.rrenat358.stats.integrations.CoreServiceIntegration;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrdersStatsService {

    private final CoreServiceIntegration coreServiceIntegration;
    private final CartServiceIntegration cartServiceIntegration;


    public Integer getNumberOfOrdersByCurrentUser(String username) {
        if (username == null || username.isBlank()) {
            return 0;
        }
        return coreServiceIntegration.getNumberOfOrdersByCurrentUser(username);
    }


    public Optional<OrderDto> getAllOrdersByCurrentUser(String username) {
        if (username == null || username.isBlank()) {
//            return null;
            OrderDto orderDtoList = new OrderDto(0L, "[username]", BigDecimal.valueOf(0), "0", "0", null);
            return Optional.ofNullable(orderDtoList);
        }
        return coreServiceIntegration.getAllOrdersByCurrentUser(username);
    }

    public Optional<ProductTopInCartDto> topProductsByAllCarts(Integer limit) {
        return cartServiceIntegration.topProductsByAllCarts(limit);
    }

    public Optional<ProductTopInOrdersDto> topProductsByAllOrders(Integer limit) {
        return coreServiceIntegration.topProductsByAllOrders(limit);
    }






}
