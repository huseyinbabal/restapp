package com.example.restapp.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.restapp.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(initializers = ProductRepositoryTests.Initializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
@Testcontainers
class ProductRepositoryTests {

    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7");

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>(MYSQL_57_IMAGE);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void it_should_create_product() {
        //given
        Product product = Product.builder().title("Black phone").price(1.28).build();
        Integer id = this.testEntityManager.persistAndGetId(product, Integer.class);
        this.testEntityManager.flush();

        //when
        Optional<Product> productOptional = this.productRepository.findById(id);

        //then
        then(productOptional).isPresent();
        Product pr = productOptional.get();
        then(pr.getTitle()).isEqualTo("Black phone");
        then(pr.getPrice()).isEqualTo(1.28);

    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("spring.datasource.url=" + mysql.getJdbcUrl(),
                "spring.datasource.username=" + mysql.getUsername(),
                "spring.datasource.password=" + mysql.getPassword())
                .applyTo(applicationContext.getEnvironment());
        }
    }
}
