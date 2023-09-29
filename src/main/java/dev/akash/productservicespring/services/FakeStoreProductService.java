package dev.akash.productservicespring.services;


import dev.akash.productservicespring.dtos.FakeStoreProductDto;
import dev.akash.productservicespring.dtos.GenericProductDto;
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
public class FakeStoreProductService implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDto dto){

        GenericProductDto genericProduct = new GenericProductDto();

        genericProduct.setId(dto.getId());
        genericProduct.setTitle(dto.getTitle());
        genericProduct.setDescription(dto.getDescription());
        genericProduct.setCategory(dto.getCategory());
        genericProduct.setPrice(dto.getPrice());
        genericProduct.setImage(dto.getImage());

        return genericProduct;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(requestUrl, FakeStoreProductDto[].class);
        List<GenericProductDto> result = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){
            GenericProductDto genericProduct = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            result.add(genericProduct);
        }

        return result;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/{id}";
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(requestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto product = response.getBody();

        return convertFakeStoreDtoToGenericProductDto(product);

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProduct) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products";
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(requestUrl, genericProduct, FakeStoreProductDto.class);
        FakeStoreProductDto product = response.getBody();

        return convertFakeStoreDtoToGenericProductDto(product);

    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/{id}";

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(requestUrl, HttpMethod.DELETE, requestCallback, responseExtractor,id);
        return convertFakeStoreDtoToGenericProductDto(response.getBody());
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/{id}";

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(requestUrl, HttpMethod.PUT, requestCallback, responseExtractor,id);
        return convertFakeStoreDtoToGenericProductDto(response.getBody());
    }

    public List<GenericProductDto> getProductsByCategory(String categoryName){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/category/{categoryName}";
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(requestUrl, FakeStoreProductDto[].class, categoryName);
        List<GenericProductDto> result = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){
            GenericProductDto genericProduct = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            result.add(genericProduct);
        }
        return result;
    }

    public List<String> getAllCategories(){

        RestTemplate restTemplate = restTemplateBuilder.build();
        String requestUrl = "https://fakestoreapi.com/products/categories";
        ResponseEntity<String[]> response = restTemplate.getForEntity(requestUrl, String[].class);

        return new ArrayList<>(Arrays.stream(response.getBody()).toList());

    }
}
