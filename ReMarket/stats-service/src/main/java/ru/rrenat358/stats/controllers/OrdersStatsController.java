package ru.rrenat358.stats.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders-stats")
@RequiredArgsConstructor
public class OrdersStatsController {


    @GetMapping("/all-orders")
    public void allOrders() {





    }




}
