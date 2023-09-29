package dev.akash.productservicespring.services;

import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {

    public List<GenericProductDto> getAllProducts();
    public GenericProductDto getProductById(Long id) throws NotFoundException;
    public GenericProductDto createProduct(GenericProductDto product);
    public GenericProductDto deleteProductById(Long id) throws NotFoundException;
    public GenericProductDto updateProductById(Long id, GenericProductDto product) throws NotFoundException;
    public List<GenericProductDto> getProductsByCategory(String categoryName);
    public List<String> getAllCategories();
}
