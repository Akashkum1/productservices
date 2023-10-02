package dev.akash.productservicespring.contollers;

import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.exceptions.NotFoundException;
import dev.akash.productservicespring.services.ProductDbService;
import dev.akash.productservicespring.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v2/products")
public class ProductDbController {

    private final ProductDbService productDbService;

    public ProductDbController(ProductDbService productDbService){
        this.productDbService = productDbService;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productDbService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return productDbService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productDbService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") UUID id) throws NotFoundException{
        return productDbService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@PathVariable("id") UUID id, @RequestBody GenericProductDto product) throws NotFoundException{
        return productDbService.updateProductById(id, product);
    }

    @GetMapping("categories/{categoryName}")
    public List<GenericProductDto> getProductsByCategory(@PathVariable("categoryName") String categoryName ){
        return productDbService.getProductsByCategory(categoryName);
    }

    @GetMapping("categories")
    public List<String> getAllCategories(){
        return productDbService.getAllCategories();
    }
}
