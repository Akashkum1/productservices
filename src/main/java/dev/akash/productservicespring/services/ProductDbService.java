package dev.akash.productservicespring.services;

import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.dtos.ProductDbDto;
import dev.akash.productservicespring.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductDbService {
    List<ProductDbDto> getAllProducts();
    ProductDbDto getProductById(UUID id) throws NotFoundException;
    ProductDbDto createProduct(ProductDbDto product);
    ProductDbDto deleteProductById(UUID id) throws NotFoundException;
    ProductDbDto updateProductById(UUID id, ProductDbDto product) throws NotFoundException;
    List<ProductDbDto> getProductsByCategory(String categoryName) throws NotFoundException;
    List<String> getAllCategories();

}
