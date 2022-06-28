package io.adarsh.springdatajpaexp.controller;

import io.adarsh.springdatajpaexp.model.Employee;
import io.adarsh.springdatajpaexp.model.PayStub;
import io.adarsh.springdatajpaexp.repos.EmployeeRepository;
import io.adarsh.springdatajpaexp.repos.PayStubRepository;
import io.adarsh.springdatajpaexp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PayStubRepository payStubRepository;

    @PostMapping("/create")
    public void create() {
        employeeService.create();
    }

    @DeleteMapping("/delete")
    public Employee delete() {
        Employee employee = employeeService.delete();
        System.out.println("Employee received");
        System.out.println("Till now external query has not been done");
        return employee;  // serialization is happening converting object into json format
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        EmployeeResponseDTO employeeResponseDTO = objectMapper.convertValue(employee, EmployeeResponseDTO.class);
//        System.out.println(employeeResponseDTO);
//        return employeeResponseDTO;
    }

    @PostMapping("/test/json")
    public void testJson(@RequestBody Employee employee) {
        System.out.println("Employee deserialized successfully");
        if (Objects.nonNull(employee.getEmailGroups())) {
            System.out.println(employee.getEmailGroups().size());
//            employee.getAccessCard().setOwner(employee);
//            employee.getPayStubs().stream().forEach(payStub -> payStub.setEmployee(employee));
            employeeRepository.save(employee);
        } else {
            System.out.println("Not Deserialized");
        }
    }

    @PostMapping("/add/paystub/{id}")
    public String addPayStub(@RequestBody PayStub payStub, @PathVariable("id") int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
//            employee.addPayStub(payStub);
            employee.getPayStubs().set(0, null);
//            payStub.setEmployee(employee);
            employeeRepository.save(employee);
            return "PayStub added successfully with id: " + payStub.getId();
        } else {
            return "Invalid employee id";
        }
    }

    @DeleteMapping("/delete/paystub/{employeeId}/{payStubId}")
    public String deletePayStub(@PathVariable("employeeId") int employeeId, @PathVariable("payStubId") int payStubId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            Optional<PayStub> optionalPayStub = payStubRepository.findById(payStubId);
            if (optionalPayStub.isPresent()) {
                PayStub payStub = optionalPayStub.get();
                employee.deletePayStub(payStub);
                employeeRepository.save(employee);
                return "PayStub successfully deleted";
            } else {
                return "Invalid payStub id";
            }
        } else {
            return "Invalid employee id";
        }
    }

}
