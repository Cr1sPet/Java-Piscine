package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.List;

public class Main
{
    public static void main( String[] args )
    {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UsersRepository repository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

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
