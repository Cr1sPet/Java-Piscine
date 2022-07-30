package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("usersRepository")
public class UsersRepositoryImpl implements UsersRepository{
    private final JdbcTemplate template;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) ->
            new User( rs.getLong("id"), rs.getString("name"), rs.getString("password"));

    @Override
    public Optional<User> findByLogin(String login) {
        List<User> userList = template.query("SELECT * FROM user_t WHERE name = ?", userRowMapper, login);
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public User findById(Long id) {
        List<User> userList = template.query("SELECT * FROM user_t WHERE id = ?", userRowMapper, id);
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM user_t", userRowMapper);
    }

    @Override
    public void save(User entity) {
        template.update("INSERT INTO user_t (name, password) values (?, ?)", entity.getName(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        template.update("UPDATE user_t SET name = ?, password = ? WHERE id = ?", entity.getName(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        template.update("DELETE FROM user_t WHERE id=?", id);
    }
}

