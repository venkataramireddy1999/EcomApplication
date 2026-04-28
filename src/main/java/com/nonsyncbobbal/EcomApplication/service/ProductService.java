package com.nonsyncbobbal.EcomApplication.service;

import com.nonsyncbobbal.EcomApplication.dto.ProductRequest;
import com.nonsyncbobbal.EcomApplication.dto.ProductResponse;
import com.nonsyncbobbal.EcomApplication.model.Product;
import com.nonsyncbobbal.EcomApplication.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    public void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageURL(productRequest.getImageURL());
    }

    public ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setCategory(product.getCategory());
        productResponse.setImageURL(product.getImageURL());
        productResponse.setIsActive(product.getIsActive());
        return productResponse;
    }

    public Optional<ProductResponse> updateProduct(int id, ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            updateProductFromRequest(product, productRequest);
             Product savedProduct = productRepository.save(product);
             return Optional.of(mapToProductResponse(savedProduct));
        }
        return Optional.empty();
    }

    public List<ProductResponse> getProducts(){
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse).
                collect(Collectors.toList());
//        List<ProductResponse> productResponseList = new ArrayList<>();
//        List<Product> products = productRepository.findAll();
//        for(Product product : products) {
//            productResponseList.add(mapToProductResponse(product));
//        }
//        return productResponseList;
    }

    public Optional<ProductResponse> getProductById(int id) {
        return productRepository.findById(id).
                map(this::mapToProductResponse);
    }
}
