package io.adarsh.springdatajpaexp.embeddable;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Embeddable
public class Address {
    @Column(name = "STREET_NAME")
    String street;
    @Column(name = "CITY_NAME")
    String city;
}
