package com.yigiter.service;


import com.yigiter.domain.Customer;
import com.yigiter.domain.Product;
import com.yigiter.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomerService customerService;

    public ProductService(ProductRepository productRepository,
                           CustomerService customerService) {
        this.productRepository = productRepository;
        this.customerService = customerService;
    }

    public List<Product> getAllProducts() {
       return productRepository.findAll();
    }

    public Product createProduct(Long customerId, Product product) {
        if (productRepository.existsByProductName(product.getProductName())){
            throw new RuntimeException("Product already exists");
        }
        Customer customer =customerService.getCustomer(customerId);
        product.setCustomers(customer);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else {
        throw new RuntimeException("product not found");
        }
    }

    public Product updateProduct(Long productId,Long customerId, Product product) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(
                ()->new RuntimeException("Product not found")
        );
        Customer customer = customerService.getCustomer(customerId);

        boolean existName =productRepository.existsByProductName(product.getProductName());
            if (existName && ! existingProduct.getProductName().equals(product.getProductName())){
                throw new RuntimeException("ProductName already exist");
            }
            existingProduct.setProductName(product.getProductName());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCustomers(customer);
            return productRepository.save(existingProduct);
    }
}
