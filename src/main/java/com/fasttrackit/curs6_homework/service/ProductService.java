package com.fasttrackit.curs6_homework.service;

import com.fasttrackit.curs6_homework.entity.Product;
import com.fasttrackit.curs6_homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> getAll(String categoryName, boolean maxprice) {
        if (!categoryName.equals("no_value")) {
            List<Product> productsByCatergory = repository.findAll()
                    .stream()
                    .filter(c -> c.getCategory().getName().equals(categoryName))
                    .collect(Collectors.toList());
            return productsByCatergory;

        } else if (maxprice == true) {
            List<Product> allProducts = repository.findAll();

            long productMaxPrice = allProducts.stream()
                    .max(Comparator.comparingLong(Product::getPrice))
                    .get()
                    .getPrice();

            List<Product> maxPriceProducts = allProducts
                    .stream()
                    .filter(m -> m.getPrice() == productMaxPrice)
                    .collect(Collectors.toList());
            return maxPriceProducts;

        } else {
            return repository.findAll();
        }
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
