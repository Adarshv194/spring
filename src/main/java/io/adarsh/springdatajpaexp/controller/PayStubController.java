package io.adarsh.springdatajpaexp.controller;

import io.adarsh.springdatajpaexp.model.PayStub;
import io.adarsh.springdatajpaexp.service.PayStubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paystub")
public class PayStubController {

    @Autowired
    private PayStubService payStubService;

    @GetMapping("/{id}")
    public PayStub getPayStub(@PathVariable("id") int id) {
        System.out.println("Paystub controller getPayStub method called");
        return payStubService.getPayStub(id);
    }
}
