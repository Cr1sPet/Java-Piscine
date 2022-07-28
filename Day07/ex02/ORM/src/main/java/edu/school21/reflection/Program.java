package edu.school21.reflection;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.reflection.entity.User;
import edu.school21.reflection.orm.OrmManager;

import java.sql.SQLException;

public class Program {

    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASS = "";

    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(DB_URL);
        ds.setUsername(USER);
        ds.setPassword(PASS);

        OrmManager ormManager = new OrmManager(ds);

        User user = new User();
        user.setId(1L);
        user.setAge(209);
        user.setLastName("Chopped");
        user.setFirstName("Josue");
        ormManager.save(user);

        user.setFirstName("Bob");
        user.setLastName("Marlie");
        user.setAge(34);
        ormManager.update(user);

        System.out.println(ormManager.findById(1L, User.class));

    }
}

