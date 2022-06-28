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
public class BoolDTO {
    List<MustDTO> must = new ArrayList<>();
    List<ShouldDTO> should = new ArrayList<>();
    List<FilterDTO> filter = new ArrayList<>();
    List<MustNotDTO> must_not = new ArrayList<>();
}
