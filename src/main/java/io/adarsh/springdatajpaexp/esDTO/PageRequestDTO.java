package io.adarsh.springdatajpaexp.esDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageRequestDTO {
    int defaultSize = 100;

    int page;
    int size;

    public int getSize() {
        return size == 0 ? defaultSize : size;
    }

    public int getPage() {
        return page;
    }
}
