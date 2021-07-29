package com.example.restapp.controllers;

import com.example.restapp.controllers.requests.CreateProductRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void it_should_create_product() throws Exception {
        //given
        CreateProductRequest request = CreateProductRequest.builder().title("White Phone").price(
            Double.valueOf("0.88")).build();

        //when
        this.mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.objectMapper.writeValueAsBytes(request))
        ).andDo(print()).andExpect(status().isOk());

        //then

    }

    @Test
    public void it_should_throw_exception_if_invalid_product_request_provided() throws Exception {
        String product = "{\"title\": \"as\", \"price\" : \"1.23\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
            .content(product)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is("Title can be min 3 max 15 characters")))
            .andExpect(MockMvcResultMatchers.content()
                .contentType(MediaType.APPLICATION_JSON));
    }

}
