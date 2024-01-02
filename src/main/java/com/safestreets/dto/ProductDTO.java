package com.safestreets.dto;

import lombok.Getter;
import lombok.Setter;
import io.micronaut.serde.annotation.Serdeable;


@Serdeable
@Getter
@Setter
public class ProductDTO {
    String name;
    String description;
}
