package com.mercadona.ean.controller;

import com.mercadona.ean.model.Product;
import com.mercadona.ean.service.EanService;
import com.mercadona.ean.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private EanService eanService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> bookList = productService.getAllProducts();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createProducts")
    public ResponseEntity<Product> createProducts(@RequestBody Product product) {
        Product productObj = productService.saveProduct(product);
        return new ResponseEntity<>(productObj, HttpStatus.CREATED);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        try {
            eanService.validateEan(product.getEan());
            Product productObj = productService.saveProduct(product);
            return new ResponseEntity<>(productObj, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {

        Optional<Product> existingProduct = productService.getProductById(id);

        if (existingProduct.isPresent()) {
            product.setId(id);
            Product productObj = productService.saveProduct(product);
            return new ResponseEntity<>(productObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
