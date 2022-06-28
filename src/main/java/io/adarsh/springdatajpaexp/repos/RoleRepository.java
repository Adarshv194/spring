package io.adarsh.springdatajpaexp.repos;

import io.adarsh.springdatajpaexp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
