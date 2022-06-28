package io.adarsh.springdatajpaexp.modelES;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.search.sort.SortOrder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle {

    String id;
    String number;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createdDate;
}
