package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class    UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private final JdbcTemplate template;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) ->
            new User( rs.getLong("id"), rs.getString("email"));

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> userList = template.query("SELECT * FROM user_table WHERE email = ?", userRowMapper, email);
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public User findById(Long id) {
        List<User> userList = template.query("SELECT * FROM user_table WHERE id = ?", userRowMapper, id);
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM user_table", userRowMapper);
    }

    @Override
    public void save(User entity) {
        template.update("INSERT INTO user_table (email) values (?)", entity.getEmail());
    }

    @Override
    public void update(User entity) {
        template.update("UPDATE user_table SET email = ? WHERE id=?", entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        template.update("DELETE FROM user_table WHERE id=?", id);
    }
}