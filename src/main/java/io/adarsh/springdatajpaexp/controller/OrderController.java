package io.adarsh.springdatajpaexp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/id")
    public Object getAllOrders() {
        // getALL from DB
        return new Object();
    }
}
