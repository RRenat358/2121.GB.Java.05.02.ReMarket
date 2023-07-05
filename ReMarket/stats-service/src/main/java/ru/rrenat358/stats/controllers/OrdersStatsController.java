package ru.rrenat358.stats.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rrenat358.stats.services.OrdersStatsService;

@RestController
@RequestMapping("api/v1/orders-stats")
@RequiredArgsConstructor
public class OrdersStatsController {

    private final OrdersStatsService ordersStatsService;


    @GetMapping("/number-of-orders-by-user")
    public Integer getNumberOfOrdersByCurrentUser(@RequestHeader String username) {
        return ordersStatsService.getNumberOfOrdersByCurrentUser(username);
    }




}
