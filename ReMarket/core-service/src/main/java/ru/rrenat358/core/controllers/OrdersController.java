package ru.rrenat358.core.controllers;

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
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(
            @RequestHeader String username,
            @RequestBody OrderDetailsDto orderDetailsDto
    ) {
        orderService.createOrder(username, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getAllOrdersByCurrentUser(@RequestHeader String username) {
        List<Order> orderList = orderService.getAllOrdersByCurrentUser(username);
        //todo сделать конвертер листов для OrderDto
        List<OrderDto> orderDtoList = orderList.stream()
                .map(order -> orderConverter.entityToDto(order)).collect(Collectors.toList());
//        List<OrderDto> orderDtoList = orderService.getAllOrdersByCurrentUser(userName).stream()
//                .map(orderConverter::entityToDto).collect(Collectors.toList());
        return orderDtoList;
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderConverter.entityToDto(orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("ORDER 404")));
    }

    @GetMapping("/number-of-orders")
    public Integer getNumberOfOrdersByCurrentUser(@RequestHeader String username) {
        return orderService.getNumberOfOrdersByCurrentUser(username);
    }


    //============================================================
    @GetMapping("/top-products-by-all-orders/{topLimit}")
    public List<ProductTopInOrdersDto> topProductsByAllOrders(@PathVariable Integer topLimit) {
        return orderService.topProductsByAllOrders(topLimit);
    }

    @GetMapping("/top-products-count-by-all-orders/{topLimit}")
    public List<Integer> topProductsByAllOrdersCount(@PathVariable Integer topLimit) {
        return orderService.topProductsCountByAllOrders(topLimit);
    }

    @GetMapping("/top-products-with-count-by-all-orders/{topLimit}")
    public LinkedHashMap<List<Integer>, List<ProductTopInOrdersDto>> topProductsByAllOrdersPC(@PathVariable Integer topLimit) {
        return orderService.topProductsWithCountByAllOrders(topLimit);
    }

    //============================================================

}
