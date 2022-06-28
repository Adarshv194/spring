package io.adarsh.springdatajpaexp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class EmailGroup {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;

    @ManyToMany(mappedBy = "emailGroups")
    @JsonBackReference
    Set<Employee> employees = new HashSet<>();
}
