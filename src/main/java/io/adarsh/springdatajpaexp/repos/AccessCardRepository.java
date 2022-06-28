package io.adarsh.springdatajpaexp.repos;

import io.adarsh.springdatajpaexp.model.AccessCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessCardRepository extends JpaRepository<AccessCard, Integer> {
}
