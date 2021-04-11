package com.fasttrackit.curs6_homework.controller;


import com.fasttrackit.curs6_homework.entity.Product;
import com.fasttrackit.curs6_homework.exceptions.ResourceNotFoundException;
import com.fasttrackit.curs6_homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    List<Product> getProducts(@RequestParam(name = "category", required = false, defaultValue = "no_value") String categoryName,
                              @RequestParam(name = "maxprice", required = false, defaultValue = "false") boolean maxprice) {
        return service.getAll(categoryName, maxprice);
    }

    @GetMapping("{productId}")
    Product getProduct(@PathVariable int productId) {
        return service.getProduct(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find product with id " + productId));
    }

    @PostMapping
    Product addStudent(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @DeleteMapping("delete/{productId}")
    void deleteProduct(@PathVariable long productId) {
        try {
            service.deleteProduct(productId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Could not find product with id " + productId);
        }
    }
}
