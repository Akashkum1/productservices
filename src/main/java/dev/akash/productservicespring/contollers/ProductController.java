package dev.akash.productservicespring.contollers;

import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public GenericProductDto deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto product){
        return productService.updateProductById(id, product);
    }

    @GetMapping("categories/{categoryName}")
    public List<GenericProductDto> getProductsByCategory(@PathVariable("categoryName") String categoryName ){
        System.out.println(categoryName);
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }
}
