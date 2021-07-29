package com.example.restapp.repositories;

import com.example.restapp.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findByTitle(String title);

}
