package com.fasttrackit.curs6_homework;

import com.fasttrackit.curs6_homework.entity.Category;
import com.fasttrackit.curs6_homework.entity.Product;
import com.fasttrackit.curs6_homework.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Curs6HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(Curs6HomeworkApplication.class, args);
    }

    @Bean
    CommandLineRunner atStartup(ProductRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                    new Product("figider", 1100, "raceste", Category.ELECTRONICS),
                    new Product("aragaz", 1100, "frige", Category.ELECTRONICS),
                    new Product("Paine", 5, "neagra", Category.FOOD)
            ));
        };
    }

}
