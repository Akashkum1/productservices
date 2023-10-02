package dev.akash.productservicespring.services;

import dev.akash.productservicespring.dtos.CategoryDbDto;
import dev.akash.productservicespring.dtos.ProductDbDto;
import dev.akash.productservicespring.exceptions.NotFoundException;
import dev.akash.productservicespring.models.Category;
import dev.akash.productservicespring.models.Product;
import dev.akash.productservicespring.repositories.CategoryRepository;
import dev.akash.productservicespring.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("selfProductServiceImpl")
@AllArgsConstructor
public class SelfProductServiceImpl implements ProductDbService{

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public ProductDbDto convertProductToProductDto(Product product){
        ProductDbDto productDto = new ProductDbDto();
        productDto.setId(product.getUuid());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(convertCategoryToCategoryDto(product.getCategory()));
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());

        return productDto;
    }

    public CategoryDbDto convertCategoryToCategoryDto(Category category){
        CategoryDbDto categoryDto = new CategoryDbDto();

        categoryDto.setId(category.getUuid());
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    public Product convertProductDtoToProduct(ProductDbDto productDto){
        Product product = new Product();

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setCategory(convertCategoryDtoToCategory(productDto.getCategory()));
        product.setPrice(productDto.getPrice());

        return product;
    }

    public Category convertCategoryDtoToCategory(CategoryDbDto categoryDto){
        Category category = new Category();

        category.setUuid(categoryDto.getId());
        category.setName(categoryDto.getName());

        return category;
    }

    @Override
    public List<ProductDbDto> getAllProducts() {
        List<Product> products = productRepository.findAll();;
        List<ProductDbDto> ans = new ArrayList<>();

        for(Product p : products){
            ans.add(convertProductToProductDto(p));
        }
        return ans;
    }

    @Override
    public ProductDbDto getProductById(UUID id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new NotFoundException("Product: " + id + " not found.");
        }
        Product productDb = product.get();
        return convertProductToProductDto(productDb);
    }

    @Override
    public ProductDbDto createProduct(ProductDbDto productDto) {
        Product product = convertProductDtoToProduct(productDto);
        product = productRepository.save(product);
        return convertProductToProductDto(product);
    }

    @Override
    public ProductDbDto deleteProductById(UUID id) throws NotFoundException {
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            productRepository.deleteById(id);
            return convertProductToProductDto(product);
        }
        else throw new NotFoundException("Product with  id "+id+" not found");
    }

    @Override
    public ProductDbDto updateProductById(UUID id, ProductDbDto product) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product that you want to update with id: " + id + " does not exist.");
        }

        Product ans = productOptional.get();
        ans.setImage(product.getImage());
        ans.setTitle(product.getTitle());
        ans.setDescription(product.getDescription());
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if(categoryOptional.isPresent()){
            ans.setCategory(categoryOptional.get());
        }
        else{
            Category category = new Category();
            category.setName(product.getCategory().getName());
            ans.setCategory(category);
        }
        ans.setPrice(product.getPrice());
        Product productDb = productRepository.save(ans);
        return convertProductToProductDto(productDb);
    }

    @Override
    public List<ProductDbDto> getProductsByCategory(String categoryName) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category Name not found in the database");
        }
        List<Product> products = categoryOptional.get().getProducts();
        List<ProductDbDto> ans = new ArrayList<>();

        for(Product p : products){
            ans.add(convertProductToProductDto(p));
        }
        return ans;

    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return  categories.stream().map(Category::getName).collect(Collectors.toList());
    }
}
