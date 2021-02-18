package com.example.northwind.api.controllers;

import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    IProductService productService;

    @GetMapping("/")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Integer productId) throws Exception {
        Product productDelete = productService.getProductById(productId);
        productService.deleteProduct(productDelete);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Silindi", Boolean.TRUE);
        return response;
    }

    @PostMapping("/new")
    public Product add(@RequestBody Product product) throws Exception {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id") Integer productId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(productId, product));
    }

}
