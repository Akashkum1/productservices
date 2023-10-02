package dev.akash.productservicespring.services;

import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductDbService {
    List<GenericProductDto> getAllProducts();
    GenericProductDto getProductById(UUID id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto deleteProductById(UUID id) throws NotFoundException;
    GenericProductDto updateProductById(UUID id, GenericProductDto product) throws NotFoundException;
    List<GenericProductDto> getProductsByCategory(String categoryName);
    List<String> getAllCategories();

}
