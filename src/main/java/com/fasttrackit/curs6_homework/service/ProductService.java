package com.fasttrackit.curs6_homework.service;

import com.fasttrackit.curs6_homework.entity.Product;
import com.fasttrackit.curs6_homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Optional<Product> getProduct(long productId) {
        return repository.findById(productId);
    }

    public void deleteProduct(long productId) {
        repository.deleteById(productId);
    }
}
