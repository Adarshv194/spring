package io.adarsh.springdatajpaexp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class PayStub {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Temporal(TemporalType.TIMESTAMP)
    Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date endDate;

    Long salary;

    @ManyToOne // In @ManyToOne fetchType is Eager by default
    @JoinColumn(name = "payStub_for")
    @JsonBackReference
    Employee employee;

    @ManyToOne
    Project project;

    @Override
    public String toString() {
        return "PayStub{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", salary=" + salary +
                '}';
    }
}
