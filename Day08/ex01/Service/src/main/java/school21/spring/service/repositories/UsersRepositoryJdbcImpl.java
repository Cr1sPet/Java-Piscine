package school21.spring.service.repositories;

import school21.spring.service.models.User;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<User> findAll() {

        String sql = "SELECT * FROM user_table";
        List<User> Users = new ArrayList<>();
        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                Users.add(new User(rs.getLong(1), rs.getString(2)));
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return Users;
    }

    @Override
    public User findById(Long id) {

        String sql = "SELECT * FROM user_table WHERE id = " + id;
        User user = null;
        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            rs.next();
            user = new User(rs.getLong(1), rs.getString(2));

        } catch (SQLException e) {
          e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User User) {
        String sql = String.format("UPDATE user_table SET email = '%s' WHERE id = %d",
                User.getEmail(),
                User.getId()
        );
        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.execute();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    @Override
    public void save(User User) {

        String sql = String.format(
                "INSERT INTO user_table (email) VALUES ('%s')",
                User.getEmail()
        );

        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            stmt.executeUpdate();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM user_table WHERE id = " + id;

        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.execute();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {

        String sql = "SELECT * FROM user_table WHERE email = '" + email + "'";
        Optional<User> retProductOptional = Optional.empty();
        try (
                Connection con = ds.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
        ) {
            rs.next();
            User user = new User(rs.getLong(1), rs.getString(2));
            retProductOptional = Optional.of(user);

        } catch (SQLException e) {
          e.printStackTrace();
        }
        return retProductOptional;
    }


}