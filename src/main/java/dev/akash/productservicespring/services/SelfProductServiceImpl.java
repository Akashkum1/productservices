package dev.akash.productservicespring.services;

import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SelfProductServiceImpl implements ProductDbService{
    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(UUID id) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(UUID id, GenericProductDto product) throws NotFoundException {
        return null;
    }

    @Override
    public List<GenericProductDto> getProductsByCategory(String categoryName) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }
}
