package io.adarsh.springdatajpaexp.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.scheduling.annotation.Async;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SectionTest {



    String name;

    KYATest kyaTest;

    @Async
    public void test() {
        Thread currentThread = Thread.currentThread();
        System.out.println("Async method is executing in " + currentThread.getName() + " thread");
    }
}
