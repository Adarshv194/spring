package io.adarsh.springdatajpaexp.esDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchRequestDTO extends PageRequestDTO {

    List<String> fields; // findByPropertyName (findByUsername)
    String searchTerm; // value to search for (adarsh)
    String sortBy;
    SortOrder sortOrder;
    List<String> personNames;
    List<String> concatenatedYearMonth;
}
