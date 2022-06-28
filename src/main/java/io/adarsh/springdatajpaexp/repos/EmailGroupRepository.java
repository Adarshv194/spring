package io.adarsh.springdatajpaexp.repos;

import io.adarsh.springdatajpaexp.model.EmailGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailGroupRepository extends JpaRepository<EmailGroup, Integer> {
}
