package ru.rrenat358.stats.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.core.ProductTopInCartDto;
import ru.rrenat358.stats.services.OrdersStatsService;

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

    @GetMapping("/top-products-in-all-carts/{limit}")
    public Optional<ProductTopInCartDto> topProductsByAllUsers(@PathVariable Integer limit) {
        return ordersStatsService.topProductsByAllUsers(limit);
    }


}
