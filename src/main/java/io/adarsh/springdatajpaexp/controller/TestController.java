package io.adarsh.springdatajpaexp.controller;

import io.adarsh.springdatajpaexp.model.KYATest;
import io.adarsh.springdatajpaexp.reposES.KYATestRepository;
import org.elasticsearch.common.io.Streams;
import org.elasticsearch.common.util.iterable.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private KYATestRepository kyaTestRepository;

    @PostMapping("/test")
    public void testCreate(@RequestBody KYATest kyaTest) {
//        kyaTestRepository.save(kyaTest);
    }

    @GetMapping("/get/test")
    public List<KYATest> getKyaTest() {
        return Streamable.of(kyaTestRepository.findAll()).toList();
    }
}
