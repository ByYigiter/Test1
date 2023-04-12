package com.yigiter.repository;

import com.yigiter.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    boolean existsByEmail(String email);

    Object findByEmail(String email);
}
