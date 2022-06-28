package io.adarsh.springdatajpaexp.DTO;

import io.adarsh.springdatajpaexp.enums.EmployeeType;
import io.adarsh.springdatajpaexp.model.AccessCard;
import io.adarsh.springdatajpaexp.model.EmailGroup;
import io.adarsh.springdatajpaexp.model.PayStub;

import java.util.*;

public class EmployeeResponseDTO {

    int id;

    String name;

    String ssn;

    Date date;

    EmployeeType employeeType;

    AccessCard accessCard;

    List<PayStub> payStubs = new ArrayList<>();

    Set<EmailGroup> emailGroups = new HashSet<>();

}
