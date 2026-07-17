package com.nonsyncbobbal.EcomApplication.mapper;

import com.nonsyncbobbal.EcomApplication.dto.ProductRequest;
import com.nonsyncbobbal.EcomApplication.dto.ProductResponse;
import com.nonsyncbobbal.EcomApplication.model.Product;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Product product) {
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
    public static void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageURL(productRequest.getImageURL());
    }
}
