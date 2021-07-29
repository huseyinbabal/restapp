package com.example.restapp.controllers.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateProductRequest {

    @NotEmpty
    @Size(min = 3, max = 15, message = "Title can be min 3 max 15 characters")
    private String title;
    
    @Min(value = 1, message = "Price shouldn't be less then 1")
    private Double price;
}
