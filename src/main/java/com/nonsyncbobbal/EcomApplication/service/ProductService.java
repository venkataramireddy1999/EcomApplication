package com.nonsyncbobbal.EcomApplication.service;

import com.nonsyncbobbal.EcomApplication.dto.ProductRequest;
import com.nonsyncbobbal.EcomApplication.dto.ProductResponse;
import com.nonsyncbobbal.EcomApplication.mapper.ProductMapper;
import com.nonsyncbobbal.EcomApplication.model.Product;
import com.nonsyncbobbal.EcomApplication.repo.OrderRepository;
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
        ProductMapper.updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductResponse(savedProduct);
    }





    public Optional<ProductResponse> updateProduct(int id, ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductMapper.updateProductFromRequest(product, productRequest);
            Product savedProduct = productRepository.save(product);
            return Optional.of(ProductMapper.mapToProductResponse(savedProduct));
        }
        return Optional.empty();
    }

    public List<ProductResponse> getProducts(){
        return productRepository.findByIsActiveTrue().stream()
                .map(ProductMapper::mapToProductResponse)
                .collect(Collectors.toList());
//        List<ProductResponse> productResponseList = new ArrayList<>();
//        List<Product> products = productRepository.findByIsActiveTrue();
//        for(Product product : products) {
//            productResponseList.add(mapToProductResponse(product));
//        }
//        return productResponseList;
    }

    public Optional<ProductResponse> getProductById(int id) {
        return productRepository.findById(id).
                map(ProductMapper::mapToProductResponse);
    }

    public boolean deleteProduct(int id) {
        return productRepository.findById(id).
                map(product -> {
                    product.setIsActive(false);
                    productRepository.save(product);
                    return true;
                }).orElse(false);
    }

    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword).stream().
                map(ProductMapper::mapToProductResponse)
                .collect(Collectors.toList());
    }
}
