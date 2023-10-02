package dev.akash.productservicespring.repositories;

import dev.akash.productservicespring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Override
    Product save(Product entity);

    @Override
    Optional<Product> findById(UUID id);

    @Override
    List<Product> findAll();

    @Override
    void deleteById(UUID id);

    List<Product> findAllByCategory_Name(String category);
}
