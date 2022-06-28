package io.adarsh.springdatajpaexp.repos;

import io.adarsh.springdatajpaexp.model.Employee;
import io.adarsh.springdatajpaexp.model.PayStub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e from Employee e JOIN FETCH e.payStubs p where e.id =:id")
    Employee findPayStub(@Param("id") int id);

    @Query("SELECT e from Employee e JOIN FETCH e.emailGroups g where e.id =:id")
    Employee findEmailGroups(@Param("id") int id);
}
