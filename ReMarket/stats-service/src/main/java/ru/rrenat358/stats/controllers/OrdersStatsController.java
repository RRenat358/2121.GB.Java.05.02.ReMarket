package ru.rrenat358.stats.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.core.ProductTopInCartDto;
import ru.rrenat358.api.core.ProductTopInOrdersDto;
import ru.rrenat358.stats.services.OrdersStatsService;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/orders-stats")
@Tag(name = "01. Статистика", description = "Методы работы со статистикой")
@RequiredArgsConstructor
public class OrdersStatsController {

    private final OrdersStatsService ordersStatsService;


    @GetMapping("/number-of-orders-by-user")
    @Operation(summary = "число/количество всех заказов данного пользователя")
    public Integer getNumberOfOrdersByCurrentUser(
            @RequestHeader(required = false) @Parameter(description = "имя пользователя")String username) {
        return ordersStatsService.getNumberOfOrdersByCurrentUser(username);
    }

    @GetMapping("/all-orders")
    @Operation(summary = "все заказы данного пользователя")
    public Optional<OrderDto> getAllOrdersByCurrentUser(
            @RequestHeader(required = false) @Parameter(description = "имя пользователя")String username) {
        return ordersStatsService.getAllOrdersByCurrentUser(username);
    }

    @GetMapping("/top-products-in-all-carts/{limit}")
    @Operation(summary = "топ продуктов добавляемых в корзину по всем пользователям")
    public Optional<ProductTopInCartDto> topProductsByAllCarts(
            @PathVariable @Parameter(description = "лимит на количество получаемых продуктов") Integer limit) {
        return ordersStatsService.topProductsByAllCarts(limit);
    }

    @GetMapping("/top-products-in-all-orders/{limit}")
    @Operation(summary = "топ продуктов во всех заказах всех пользователей")
    public Optional<ProductTopInOrdersDto> topProductsByAllOrders(
            @PathVariable @Parameter(description = "лимит на количество получаемых продуктов") Integer limit) {
        return ordersStatsService.topProductsByAllOrders(limit);
    }


}
