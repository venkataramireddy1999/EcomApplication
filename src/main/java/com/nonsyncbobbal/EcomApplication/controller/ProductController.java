package com.nonsyncbobbal.EcomApplication.controller;

import com.nonsyncbobbal.EcomApplication.dto.ProductRequest;
import com.nonsyncbobbal.EcomApplication.dto.ProductResponse;
import com.nonsyncbobbal.EcomApplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {

        return new ResponseEntity<ProductResponse>(productService.createProduct(productRequest),
                                                    HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable int id,
                                                         @RequestBody ProductRequest productRequest) {
        if(productService.updateProduct(id,productRequest).isPresent())
            return ResponseEntity.ok(productService.updateProduct(id,productRequest).get());
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        if (productService.getProductById(id).isPresent())
            return ResponseEntity.ok(productService.getProductById(id).get());
        return ResponseEntity.notFound().build();
    }
}
