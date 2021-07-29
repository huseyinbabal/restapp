package com.example.restapp.clients;

import com.example.restapp.clients.models.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "categories", url = "${application.category-api-url}")
public interface CategoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/categories/{id}")
    Category getCategory(@PathVariable Integer id);
}
