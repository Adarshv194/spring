package io.adarsh.springdatajpaexp.service;

import io.adarsh.springdatajpaexp.model.PayStub;
import io.adarsh.springdatajpaexp.repos.PayStubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayStubService {

    @Autowired
    private PayStubRepository payStubRepository;

    public PayStub getPayStub(int id) {
        return payStubRepository.findById(id).orElse(null);
    }
}
