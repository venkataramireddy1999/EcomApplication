package com.nonsyncbobbal.EcomApplication.repo;

import com.nonsyncbobbal.EcomApplication.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
