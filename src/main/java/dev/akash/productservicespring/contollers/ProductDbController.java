package dev.akash.productservicespring.contollers;

import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.dtos.ProductDbDto;
import dev.akash.productservicespring.exceptions.NotFoundException;
import dev.akash.productservicespring.services.ProductDbService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v2/products")
public class ProductDbController {

    private final ProductDbService productDbService;

    public ProductDbController(@Qualifier("selfProductServiceImpl") ProductDbService productDbService){
        this.productDbService = productDbService;
    }
    @GetMapping
    public List<ProductDbDto> getAllProducts(){
        return productDbService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDbDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return productDbService.getProductById(id);
    }

    @PostMapping
    public ProductDbDto createProduct(@RequestBody ProductDbDto product){
        return productDbService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ProductDbDto deleteProductById(@PathVariable("id") UUID id) throws NotFoundException{
        return productDbService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDbDto updateProductById(@PathVariable("id") UUID id, @RequestBody ProductDbDto product) throws NotFoundException{
        return productDbService.updateProductById(id, product);
    }

    @GetMapping("categories/{categoryName}")
    public List<ProductDbDto> getProductsByCategory(@PathVariable("categoryName") String categoryName ) throws NotFoundException{
        return productDbService.getProductsByCategory(categoryName);
    }

    @GetMapping("categories")
    public List<String> getAllCategories(){
        return productDbService.getAllCategories();
    }
}
