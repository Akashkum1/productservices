package dev.akash.productservicespring.services;

import dev.akash.productservicespring.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.akash.productservicespring.dtos.GenericProductDto;
import dev.akash.productservicespring.exceptions.NotFoundException;
import dev.akash.productservicespring.thirdpartyclients.productservice.fakestore.FakeStoreServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private final FakeStoreServiceClient fakeStoreServiceClient;

    public FakeStoreProductService(FakeStoreServiceClient fakeStoreServiceClient){
        this.fakeStoreServiceClient = fakeStoreServiceClient;
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
        List<FakeStoreProductDto> response = fakeStoreServiceClient.getAllProducts();
        List<GenericProductDto> result = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: response){
            GenericProductDto genericProduct = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            result.add(genericProduct);
        }
        return result;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreServiceClient.getProductById(id));

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProduct) {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreServiceClient.createProduct(genericProduct));
    }

    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) throws NotFoundException {
        return convertFakeStoreDtoToGenericProductDto(fakeStoreServiceClient.updateProductById(id,product));
    }

    @Override
    public List<GenericProductDto> getProductsByCategory(String categoryName){
        List<FakeStoreProductDto> response = fakeStoreServiceClient.getProductsByCategory(categoryName);
        List<GenericProductDto> result = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: response){
            GenericProductDto genericProduct = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDto);
            result.add(genericProduct);
        }
        return result;
    }

    @Override
    public List<String> getAllCategories(){
        return fakeStoreServiceClient.getAllCategories();
    }
}
