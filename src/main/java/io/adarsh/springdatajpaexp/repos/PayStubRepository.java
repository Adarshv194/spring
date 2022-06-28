package io.adarsh.springdatajpaexp.repos;

import io.adarsh.springdatajpaexp.model.PayStub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayStubRepository extends JpaRepository<PayStub, Integer> {
}
