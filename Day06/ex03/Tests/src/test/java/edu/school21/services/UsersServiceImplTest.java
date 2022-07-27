package edu.school21.services;

import edu.school21.UsersServiceImpl;
import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;

import static org.mockito.Mockito.*;

public class UsersServiceImplTest {

    private UsersRepository repository = Mockito.mock(UsersRepository.class);
    private UsersServiceImpl service;
    private final User EXPECTED_CORRECT_USER = new User(1, "Mike", "12345", false);
    private final User EXPECTED_INCORRECT_LOGIN_USER = new User(2, "Bob", "12345", false);
    private final User EXPECTED_INCORRECT_PASSWORD_USER = new User(1, "Mike", "123456", false);



    @BeforeEach
    void init() {
        service = new UsersServiceImpl(repository);
        when(repository.findByLogin(EXPECTED_CORRECT_USER.getLogin())).thenReturn(EXPECTED_CORRECT_USER);
        when(repository.findByLogin(EXPECTED_INCORRECT_PASSWORD_USER.getLogin())).thenReturn(EXPECTED_CORRECT_USER);
        doNothing().when(repository).update(EXPECTED_CORRECT_USER);
    }

    @Test
    void testCorrectUser() {
        Assertions.assertTrue(service.authenticate(EXPECTED_CORRECT_USER.getLogin(), EXPECTED_CORRECT_USER.getPassword()));
        verify(repository).update(EXPECTED_CORRECT_USER);
    }

    @Test
    void testIncorrectLogin() {
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> service.authenticate(EXPECTED_INCORRECT_LOGIN_USER.getLogin(), EXPECTED_INCORRECT_LOGIN_USER.getPassword()));
        verify(repository, Mockito.never()).update(EXPECTED_INCORRECT_LOGIN_USER);
    }

    @Test
    void testIncorrectPassword() {
        Assertions.assertFalse(service.authenticate(EXPECTED_INCORRECT_PASSWORD_USER.getLogin(),
                EXPECTED_INCORRECT_PASSWORD_USER.getPassword()));
        verify(repository, Mockito.never()).update(EXPECTED_INCORRECT_LOGIN_USER);
    }



}
