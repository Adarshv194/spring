package io.adarsh.springdatajpaexp.esDTO;

import io.adarsh.springdatajpaexp.modelES.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ESBindDTO {
    Person _source;
}
