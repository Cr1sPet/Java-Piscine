package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Product> findAll() throws SQLException {

        String sql = "SELECT * FROM product";
        List<Product> products = new ArrayList<>();
        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {

        String sql = "SELECT * FROM product WHERE id = " + id;
        Optional<Product> retProductOptional = Optional.empty();
        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            rs.next();
            Product product = new Product();
            product.setId(rs.getInt(1));
            product.setName(rs.getString(2));
            product.setPrice(rs.getInt(3));

        }
        return retProductOptional;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }
}
