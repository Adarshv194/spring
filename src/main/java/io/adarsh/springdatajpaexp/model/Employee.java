package io.adarsh.springdatajpaexp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.adarsh.springdatajpaexp.embeddable.Address;
import io.adarsh.springdatajpaexp.embeddable.IdName;
import io.adarsh.springdatajpaexp.enums.EmployeeType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;
import org.springframework.core.annotation.Order;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "EMPLOYEE_DATA")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "EMPLOYEE_NAME", length = 100)
    String name;

    @Column(unique = true, nullable = false, updatable = false)
    String ssn;

    @Temporal(TemporalType.DATE)
    Date date;

    @Enumerated(EnumType.STRING)
    EmployeeType employeeType;

    @OneToOne(mappedBy = "owner", orphanRemoval = true, cascade = { CascadeType.PERSIST }) // with orphanRemoval in OneToOne only CascadeType.Persist can work for doing the mapping, insertion new and deletion
    @JsonBackReference
    AccessCard accessCard;

    @OneToMany(mappedBy = "founder", orphanRemoval = true, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JsonManagedReference
    List<AccessCard> foundAccessCards;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.MERGE, CascadeType.PERSIST }) // with orphanRemoval in OneToMany we need CascadeType.Persist for saving and deleting but also CascadeType.Merge for doing the mapping
    @JsonManagedReference
    List<PayStub> payStubs = new ArrayList<>(); // In OneToMany - fetchType is Lazy by defaultEmbedded

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH) // In @ManyToMany - fetchType is Lazy by default
//    @JoinTable(
//            name = "EMAIL_GROUP_SUBSCRIPTION",
//            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
//            inverseJoinColumns = @JoinColumn(name = "SUBSCRIPTION_EMAIL_GROUP_ID")
//    )
//    @JsonManagedReference
//    Set<EmailGroup> emailGroups = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "EMAIL_GROUP_SUBSCRIPTION",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBSCRIPTION_EMAIL_GROUP_ID")
    )
//    @JsonManagedReference
    List<EmailGroup> emailGroups = new ArrayList<>();

//    @ManyToMany
//    @JoinColumn(name = "mapping_id")
//    List<Project> projects;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET_NAME")),
//            @AttributeOverride(name = "city", column = @Column(name = "HOME_CITY_NAME"))
//    })
//    Address homeAddress;
//
//    @Embedded
//    Address officeAddress;

    @ElementCollection
    @JoinTable(name = "USER_ADDRESS",
               joinColumns = @JoinColumn(name = "USER_ID")
    )
    @GenericGenerator(name = "sequence", strategy = "sequence")
    @CollectionId(column = @Column(name = "ADDRESS_ID"), generator ="sequence", type = @Type(type = "long"))
    Collection<Address> addresses = new ArrayList<>();

    @Transient
    List<Integer> numbers = new ArrayList<>();

    int salary;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ssn='" + ssn + '\'' +
                ", date=" + date +

                ", employeeType=" + employeeType +
                ", accessCard=" + accessCard +
                ", payStubs=" + payStubs +
                ", emailGroups=" + emailGroups +
                '}';
    }

    public void addPayStub(PayStub payStub) {
        payStubs.add(payStub);
        payStub.setEmployee(this);
    }

    public void addEmailGroup(EmailGroup emailGroup) {
        emailGroups.add(emailGroup);
    }

    public void deletePayStub(PayStub payStub) {
        this.payStubs.remove(payStub);
    }

    public void deletePayStub(List<PayStub> payStubs) {
        this.payStubs.removeAll(payStubs);
    }

    public Employee(String name, List<Integer> numbers, int salary) {
        this.numbers = numbers;
        this.name = name;
        this.salary = salary;
    }
}
