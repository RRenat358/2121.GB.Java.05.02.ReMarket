package ru.rrenat358.stats.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.stats.services.OrdersStatsService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/orders-stats")
@RequiredArgsConstructor
public class OrdersStatsController {

    private final OrdersStatsService ordersStatsService;


    @GetMapping("/number-of-orders-by-user")
    public Integer getNumberOfOrdersByCurrentUser(@RequestHeader(value = "username", required = false ) String username) {
        return ordersStatsService.getNumberOfOrdersByCurrentUser(username);
    }

    @GetMapping("/all-orders")
    public List<OrderDto> getAllOrdersByCurrentUser(@RequestHeader(value = "username", required = false ) String username) {
        if (username == null || username.isBlank()) {
//            return null;
//            return new ArrayList<>();
            List<OrderDto> orderDtoList = Collections.singletonList(new OrderDto(0L, "[username]", BigDecimal.valueOf(0.001), "0", "0", null));
            return orderDtoList;
        }
        return ordersStatsService.getAllOrdersByCurrentUser(username);
    }




}
