package com.nonsyncbobbal.EcomApplication.repo;

import com.nonsyncbobbal.EcomApplication.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
