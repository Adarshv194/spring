package io.adarsh.springdatajpaexp.service;

import io.adarsh.springdatajpaexp.embeddable.Address;
import io.adarsh.springdatajpaexp.embeddable.IdName;
import io.adarsh.springdatajpaexp.enums.EmployeeType;
import io.adarsh.springdatajpaexp.model.*;
import io.adarsh.springdatajpaexp.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@Scope(value = "prototype")
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccessCardRepository accessCardRepository;

    @Autowired
    private PayStubRepository payStubRepository;

    @Autowired
    private EmailGroupRepository emailGroupRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public void create() {
        Employee employee1 = new Employee();
        employee1.setName("Adarsh");
        employee1.setDate(new Date());
        employee1.setSsn("123");
        employee1.setEmployeeType(EmployeeType.FULL_TIME);

        Employee employee2 = new Employee();
        employee2.setName("Chicky");
        employee2.setDate(new Date());
        employee2.setSsn("124");
        employee2.setEmployeeType(EmployeeType.CONTRACT);

        AccessCard accessCard1 = new AccessCard();
        accessCard1.setIsActive(true);
        accessCard1.setFirmwareVersion("V1.1.0");
        accessCard1.setIssuedDate(new Date());

        AccessCard accessCard2 = new AccessCard();
        accessCard2.setIssuedDate(new Date());
        accessCard2.setIsActive(false);
        accessCard2.setFirmwareVersion("V1.1.0");

//        employee1.setAccessCard(accessCard1); // mapping is managed by employee only
//        employee2.setAccessCard(accessCard2);
		accessCard1.setOwner(employee1);
		accessCard2.setOwner(employee2);

        PayStub payStub1 = new PayStub();
        payStub1.setStartDate(new Date());
        payStub1.setEndDate(new Date());
        payStub1.setSalary(35000L);

        PayStub payStub2 = new PayStub();
        payStub2.setStartDate(new Date());
        payStub2.setEndDate(new Date());
        payStub2.setSalary(32000L);

        PayStub payStub3 = new PayStub();
        payStub3.setStartDate(new Date());
        payStub3.setEndDate(new Date());
        payStub3.setSalary(32500L);

        payStub1.setEmployee(employee1);
        payStub2.setEmployee(employee1);
        payStub3.setEmployee(employee2);

        EmailGroup emailGroup1 = new EmailGroup();
        emailGroup1.setName("General");
        EmailGroup emailGroup2 = new EmailGroup();
        emailGroup2.setName("Gaming");
        EmailGroup emailGroup3 = new EmailGroup();
        emailGroup3.setName("NSWS");

        employee1.addEmailGroup(emailGroup1);
        employee1.addEmailGroup(emailGroup2);
        employee2.addEmailGroup(emailGroup3);

        IdName idName = new IdName();
        idName.setFirstName("adarsh");
        idName.setLastName("verma");

        Project project = new Project();
        project.setId(idName);
        payStub1.setProject(project);
        payStub2.setProject(project);

        Project project1 = new Project();
        project1.setId(idName); // if we provide same embedded id then it will not create a new record for the same

        Address address1 = new Address();
        address1.setCity("New Delhi");
        address1.setStreet("Street number - 5");

        Address address2 = new Address();
        address2.setCity("Jaipur");
        address2.setStreet("Street number - 5");

        employee1.getAddresses().add(address1);
        employee1.getAddresses().add(address2);

        // Persisting entities

//        accessCardRepository.save(accessCard1);
//        accessCardRepository.save(accessCard2);

        emailGroupRepository.save(emailGroup1);
        emailGroupRepository.save(emailGroup2);
        emailGroupRepository.save(emailGroup3);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        accessCardRepository.save(accessCard1);
        accessCardRepository.save(accessCard2);

        projectRepository.save(project);
        projectRepository.save(project1);

        payStubRepository.save(payStub1);
        payStubRepository.save(payStub2);
        payStubRepository.save(payStub3);
    }

    public Employee delete() {
//        Employee employee;
//        System.out.println("Fetching Pay stubs");
//        employee = employeeRepository.findPayStub(4);
//        System.out.println("Fetching Email groups");
//        employee = employeeRepository.findEmailGroups(4);
//        System.out.println("Testing Pay stubs now");
//        List<PayStub> payStubs = employee.getPayStubs();
//        payStubs.get(0);
//        System.out.println("Returning employee now");
//        return employee;
        Optional<Employee> optionalEmployee = employeeRepository.findById(4);
        if(optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
//            employee.getPayStubs().get(0);
//            employee.getEmailGroups().stream().forEach(email -> System.out.println(email));
            return employee;
        } else {
            System.out.println("Record not found");
            return null;
        }
    }
}
