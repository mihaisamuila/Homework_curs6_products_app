package com.fasttrackit.curs6_homework.repository;

import com.fasttrackit.curs6_homework.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
