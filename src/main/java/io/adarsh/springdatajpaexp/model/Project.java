package io.adarsh.springdatajpaexp.model;

import io.adarsh.springdatajpaexp.embeddable.IdName;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Project {

//    @Id @GeneratedValue(strategy = GenerationType.AUTO)
//    int id;

    @EmbeddedId
    IdName id;

    int t;

    @OneToMany(mappedBy = "project")
    List<PayStub> payStubs = new ArrayList<>();

//    @ManyToMany
//    @JoinColumn(name = "mapping_id")
//    List<Employee> employees;

}
