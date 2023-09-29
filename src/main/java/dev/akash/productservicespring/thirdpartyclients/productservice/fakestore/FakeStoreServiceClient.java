package dev.akash.productservicespring.thirdpartyclients.productservice.fakestore;

import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreServiceClient{

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(requestUrl, FakeStoreProductDto[].class);
        return new ArrayList<>(Arrays.stream(response.getBody()).toList());
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/{id}";
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(requestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto product = response.getBody();

        if(product == null){
            throw new NotFoundException("Product with id " + id + " doesn't exits");
        }

        return product;

    }

    public FakeStoreProductDto createProduct(GenericProductDto genericProduct) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(requestUrl, genericProduct, FakeStoreProductDto.class);
        FakeStoreProductDto product = response.getBody();

        return product;

    }

    public FakeStoreProductDto deleteProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/{id}";

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(requestUrl, HttpMethod.DELETE, requestCallback, responseExtractor,id);
        FakeStoreProductDto product = response.getBody();
        if(product == null){
            throw new NotFoundException("Product with id " + id + " doesn't exits");
        }
        return product;
    }

    public FakeStoreProductDto updateProductById(Long id, GenericProductDto product) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/{id}";

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(requestUrl, HttpMethod.PUT, requestCallback, responseExtractor,id);
        FakeStoreProductDto fakeProduct = response.getBody();
        if(fakeProduct == null){
            throw new NotFoundException("Product with id " + id + " doesn't exits");
        }
        return fakeProduct;
    }

    public List<FakeStoreProductDto> getProductsByCategory(String categoryName){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/category/{categoryName}";
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(requestUrl, FakeStoreProductDto[].class, categoryName);

        return new ArrayList<>(Arrays.stream(response.getBody()).toList());
    }

    public List<String> getAllCategories(){

        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/categories";
        ResponseEntity<String[]> response = restTemplate.getForEntity(requestUrl, String[].class);

        return new ArrayList<>(Arrays.stream(response.getBody()).toList());

    }
}

