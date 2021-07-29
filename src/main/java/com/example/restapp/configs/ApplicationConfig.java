package com.example.restapp.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationConfig {

    private String categoryApiUrl;
}
