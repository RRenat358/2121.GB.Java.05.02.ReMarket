package ru.rrenat358.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.rrenat358.api.core.OrderDetailsDto;
import ru.rrenat358.api.core.OrderDto;
import ru.rrenat358.api.core.ProductTopInOrdersDto;
import ru.rrenat358.api.exceptions.ResourceNotFoundException;
import ru.rrenat358.core.converters.OrderConverter;
import ru.rrenat358.core.entities.Order;
import ru.rrenat358.core.entities.Product;
import ru.rrenat358.core.services.OrderService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "02. Заказы", description = "Методы работы с заказами")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "оформить/создать заказ (но ещё не оплатить)"
    )
    public void createOrder(
            @RequestHeader String username,
            @RequestBody OrderDetailsDto orderDetailsDto
    ) {
        orderService.createOrder(username, orderDetailsDto);
    }


    @GetMapping
    @Operation(
            summary = "получить все заказы текущего пользователя"
    )
    public List<OrderDto> getAllOrdersByCurrentUser(@RequestHeader @Parameter(description = "имя пользователя") String username) {
        List<Order> orderList = orderService.getAllOrdersByCurrentUser(username);
        //todo сделать конвертер листов для OrderDto
        List<OrderDto> orderDtoList = orderList.stream()
                .map(order -> orderConverter.entityToDto(order)).collect(Collectors.toList());
//        List<OrderDto> orderDtoList = orderService.getAllOrdersByCurrentUser(userName).stream()
//                .map(orderConverter::entityToDto).collect(Collectors.toList());
        return orderDtoList;
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "найти заказ по id"
    )
    public OrderDto getOrderById(@PathVariable @Parameter(description = "id заказа") Long id) {
        return orderConverter.entityToDto(orderService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ORDER 404")));
    }


    @GetMapping("/number-of-orders")
    @Operation(
            summary = "получить общее количество заказов текущего пользователя"
    )
    public Integer getNumberOfOrdersByCurrentUser(
            @RequestHeader @Parameter(description = "имя пользователя") String username
    ) {
        return orderService.getNumberOfOrdersByCurrentUser(username);
    }


    @GetMapping("/top-products-in-all-orders/{limit}")
    @Operation(
            summary = "топ продуктов во всех заказах всех пользователей"
    )
    public List<ProductTopInOrdersDto> topProductsByAllOrders(
            @PathVariable @Parameter(description = "лимит на количество получаемых продуктов") Integer limit
    ) {
        return orderService.topProductsByAllOrders(limit);
    }




}
