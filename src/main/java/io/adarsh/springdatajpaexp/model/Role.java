package io.adarsh.springdatajpaexp.model;

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
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;

    @ManyToMany(mappedBy = "roles")
    List<User> user = new ArrayList<>();

    public Role(String name) {
        this.name = name;
    }
}
