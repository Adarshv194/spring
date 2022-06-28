package io.adarsh.springdatajpaexp.esDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonESDTO {

    String id;

    String name;

    String year;

    String month;

    String concatenatedYearMonth;
}
