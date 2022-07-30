package edu.school21.sockets.services;


import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("usersService")
public class UsersServiceImpl implements UsersService {

    private PasswordEncoder passwordEncoder;
    private UsersRepository repository;

    @Autowired
    public UsersServiceImpl(PasswordEncoder passwordEncoder, UsersRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public boolean signUp(String name, String password) {
        if (repository.findByLogin(name).isPresent()) {
            return false;
        }

        repository.save(new User(null, name, passwordEncoder.encode(password)));
        return true;
    }

}
