package io.adarsh.springdatajpaexp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ESQueryDTO {
    List<String> _source = new ArrayList<>();
    String size;
    QueryESDTO query;
}
