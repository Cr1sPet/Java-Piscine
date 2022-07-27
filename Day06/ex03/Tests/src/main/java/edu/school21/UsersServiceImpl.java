package edu.school21;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {

    private UsersRepository repository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.repository = usersRepository;
    }

    public boolean authenticate(String login, String password) {

        User user = repository.findByLogin(login);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        if (user.isAuthSuccess()) {
            throw new AlreadyAuthenticatedException();
        }

        if (user.getPassword().equals(password)) {
            user.setAuthSuccess(true);
        } else {
            return false;
        }

        repository.update(user);

        return true;
    }

}
