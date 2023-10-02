package dev.akash.productservicespring.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Price extends BaseModal {
    private String currency;
    private double price;
}
