package dev.akash.productservicespring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Product extends BaseModal{
    private String title;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    private double price;
}
