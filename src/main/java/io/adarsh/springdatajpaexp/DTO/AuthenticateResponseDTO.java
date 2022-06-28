package io.adarsh.springdatajpaexp.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticateResponseDTO {

    final String Jwt;

    public AuthenticateResponseDTO(String jwt) {
        Jwt = jwt;
    }
}
