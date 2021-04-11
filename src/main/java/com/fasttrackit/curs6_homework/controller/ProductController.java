package com.fasttrackit.curs6_homework.controller;


import com.fasttrackit.curs6_homework.entity.Product;
import com.fasttrackit.curs6_homework.exceptions.ResourceNotFoundException;
import com.fasttrackit.curs6_homework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    List<Product> getProduct() {
        return service.getAll();
    }

    @GetMapping("category/{categoryName}")
    List<Product> getProductByCategory(@PathVariable String categoryName) {
        List<Product> allProducts = service.getAll();

        List<Product> productsByCatergory = allProducts
                .stream()
                .filter(c -> c.getCategory().getName().equals(categoryName))
                .collect(Collectors.toList());
        return productsByCatergory;
    }

    @GetMapping("maxprice")
    List<Product> getMaxPriceProduct() {
        List<Product> allProducts = service.getAll();

        long productMaxPrice = allProducts.stream()
                .max(Comparator.comparingLong(Product::getPrice))
                .get()
                .getPrice();

        List<Product> maxPriceProducts = allProducts
                .stream()
                .filter(m -> m.getPrice() == productMaxPrice)
                .collect(Collectors.toList());

        return maxPriceProducts;
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
