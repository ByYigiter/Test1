package com.yigiter.repository;

import com.yigiter.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    boolean existsByProductName(String productName);
}
