package dev.akash.productservicespring.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class ProductDbDto {
    private UUID id;
    private String title;
    private double price;
    private CategoryDbDto category;
    private String description;
    private String image;
}
