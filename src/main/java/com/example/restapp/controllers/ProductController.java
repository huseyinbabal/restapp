package com.example.restapp.controllers;

import javax.validation.Valid;

import com.example.restapp.controllers.requests.CreateProductRequest;
import com.example.restapp.dto.ProductDto;
import com.example.restapp.models.Product;
import com.example.restapp.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void create(@Valid @RequestBody CreateProductRequest request) {
        this.productService.create(request);
    }

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable Integer id) {
        return this.productService.findById(id);
    }
}
