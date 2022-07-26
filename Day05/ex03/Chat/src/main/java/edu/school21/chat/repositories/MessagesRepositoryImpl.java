package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Chatroom;
import exceptions.NotSavedSubEntityException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MessagesRepositoryImpl implements MessagesRepository {

    DataSource ds;

    public MessagesRepositoryImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Message> findById(Long id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Message message = new Message();
        Optional<Message> retMessageOptional = Optional.empty();
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM messages " +
                    "JOIN chatroom c on c.id = messages.room_id " +
                    "JOIN users u on u.id = messages.author_id " +
                    "WHERE messages.id = " + id);
            rs = stmt.executeQuery();
            rs.next();

            message.setId(id);
            message.setText(rs.getString(4));
            message.setDate(
                    LocalDateTime.parse(
                            rs.getString(5),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    )
            );
            message.setRoom(
                    new Chatroom(
                            rs.getLong(7),
                            rs.getString(9),
                            null,
                            null
                    )
            );
            message.setAuthor(
                    new User(
                            rs.getLong(9),
                            rs.getString(10),
                            rs.getString(11),
                            null,
                            null
                    )
            );
            retMessageOptional = Optional.of(message);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        return retMessageOptional;
    }

    private void checkAuthor(User author, Connection connection) throws SQLException {

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE id = " + author.getId();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next() == false) {
                throw new NotSavedSubEntityException("Cannot save message because Cannot find author by id : " + author.getId());
            }
    }

    private void checkRoom(Chatroom chatroom, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        String query = "SELECT * FROM chatroom WHERE id = " + chatroom.getId();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next() == false) {
            throw new NotSavedSubEntityException("Cannot save message because Cannot find author by id : " + chatroom.getId());
        }
    }

    @Override
    public void save(Message message) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet key = null;


        try {
            conn = ds.getConnection();
            checkRoom(message.getRoom(), conn);
            checkAuthor(message.getAuthor(), conn);
            String sql = String.format(
                    "INSERT INTO messages (room_id, author_id, message, time) VALUES (%d, %d, '%s', '%s')",
                    message.getRoom().getId(),
                    message.getAuthor().getId(),
                    message.getText(),
                    message.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            );
            System.out.println(sql);
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.execute();
            key = stmt.getGeneratedKeys();
            key.next();
            message.setId(key.getLong(1));

        } catch(SQLException e) {
            System.out.println(e);
        } finally {
            try { if (key != null) key.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

    @Override
    public boolean update(Message message) {

        String sql = String.format("UPDATE  messages SET room_id = %d, author_id = %d, message = '%s', time = '%s'" +
                        "WHERE id = %d",
                message.getRoom().getId(),
                message.getAuthor().getId(),
                message.getText(),
                message.getDate().format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                ),
                message.getId()
        );

        try (Connection con = ds.getConnection()){

            checkRoom(message.getRoom(), con);
            checkAuthor(message.getAuthor(), con);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }
}
