package dev.akash.productservicespring.services;

import dev.akash.productservicespring.dtos.GenericProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductService {

    public List<GenericProductDto> getAllProducts();
    public GenericProductDto getProductById(Long id);
    public GenericProductDto createProduct(GenericProductDto product);
    public GenericProductDto deleteProductById(Long id);
    public GenericProductDto updateProductById(Long id, GenericProductDto product);
    public List<GenericProductDto> getProductsByCategory(String categoryName);
    public List<String> getAllCategories();
}
