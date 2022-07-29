package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;
import school21.spring.service.repositories.CrudRepository;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class Main
{

    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASS = "";



    public static void main( String[] args )
    {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(DB_URL);
        ds.setUsername(USER);
        ds.setPassword(PASS);

        UsersRepository repository = new UsersRepositoryJdbcImpl(ds);

        for(Long i = 0L; i < 10; i++) {
            User user = new User((i + 1), "email" + i);
            repository.save(user);
            System.out.println(user);

        }

        System.out.println("##########################");

        List<User> users = repository.findAll();

        for (User user : users) {
            System.out.println(user);
        }

        users.get(4).setEmail("get4");

        repository.update(users.get(4));

        System.out.println("##########################");

        System.out.println("Updated user[4] by email : get4, Result : ");

        System.out.println(repository.findById(users.get(4).getId()));


    }
}
