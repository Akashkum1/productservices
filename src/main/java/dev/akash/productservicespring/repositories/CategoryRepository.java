package dev.akash.productservicespring.repositories;

import dev.akash.productservicespring.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Override
    <S extends Category> S save(S entity);


    Optional<Category> findByName(String name);

    @Override
    Optional<Category> findById(UUID uuid);

    @Override
    List<Category> findAll();


    List<Category>  findAllByName(String categoryName);

    @Override
    List<Category> findAllById(Iterable<UUID> uuids);
}