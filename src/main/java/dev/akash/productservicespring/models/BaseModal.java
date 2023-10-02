package dev.akash.productservicespring.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModal {

    @Id
    private Long id;
}
