package com.yigiter.controller;

import com.yigiter.domain.Product;
import com.yigiter.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @PostMapping("{customerId}")
    public ResponseEntity<Product> createProduct(@PathVariable Long customerId,@Validated @RequestBody Product product) {
        Product savedProduct = productService.createProduct(customerId,product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestParam("productId") Long productId,
                                                 @RequestParam("customerId") Long customerId,
                                                 @Validated @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId,customerId, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
       productService.deleteProduct(id);
       String message ="Product is deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

