package ru.rrenat358.stats.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.core.ProductDto;
import ru.rrenat358.api.core.ProductDtoTopInCart;
import ru.rrenat358.stats.services.OrdersStatsService;

import java.util.LinkedHashMap;
import java.util.Map;
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
    public Optional<OrderDto> getAllOrdersByCurrentUser(@RequestHeader(value = "username", required = false ) String username) {
        return ordersStatsService.getAllOrdersByCurrentUser(username);
    }

    @GetMapping("/top-products-by-all-users/{limit}")
    public Optional<ProductDtoTopInCart> topProductsByAllUsers(@PathVariable Integer limit) {
        return ordersStatsService.topProductsByAllUsers(limit);
    }


}
