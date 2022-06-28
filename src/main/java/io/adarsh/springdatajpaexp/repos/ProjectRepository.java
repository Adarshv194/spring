package io.adarsh.springdatajpaexp.repos;

import io.adarsh.springdatajpaexp.embeddable.IdName;
import io.adarsh.springdatajpaexp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, IdName> {
}
