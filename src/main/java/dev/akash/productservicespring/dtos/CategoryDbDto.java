package dev.akash.productservicespring.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CategoryDbDto {
    private UUID id;
    private String name;
}
