package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {

    private DataSource dataSource;
    private ProductsRepositoryJdbcImpl repositoryJdbc;

    final Product EXPECTED_UPDATED_PRODUCT = new Product(1, "ball", 150);
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(new Product (1, "ball", 150),
            new Product (2, "sneakers", 500),
            new Product (3, "shorts", 300),
            new Product (4, "T-shirt", 450),
            new Product (5, "backpack", 300));

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        repositoryJdbc = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void testFindAll() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, repositoryJdbc.findAll());
    }

    @Test
    void testFindById() throws SQLException {
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repositoryJdbc.findById(1L).get());
    }


}
