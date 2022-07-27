package edu.school21.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class EmbeddedDataSourceTest {

    private DataSource dataSource;

    @BeforeEach
    void init() {
         dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Test
    void isDataSourceConnectionNotNull() {
        try {
            Assertions.assertNotNull(dataSource.getConnection());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
