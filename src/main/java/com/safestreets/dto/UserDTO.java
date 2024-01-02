package com.safestreets.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
public class UserDTO {
    String name;
    String email;
    String role;
}
