package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.models.Chatroom;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            stmt = conn.prepareStatement("SELECT * FROM messages WHERE id = " + id);
            rs = stmt.executeQuery();
            if (rs != null) {
                rs.next();
                Optional<User> user = findUserById(rs.getLong(2));
                Optional<Chatroom> chatroom = findRoomById(rs.getLong(3));
                if (user.isPresent() && chatroom.isPresent()) {
                    message.setAuthor(user.get());
                    message.setId(id);
                    message.setText(rs.getString(4));
                    message.setRoom(chatroom.get());
                    retMessageOptional = Optional.of(message);
                }
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        return retMessageOptional;
    }

    private Optional<User> findUserById(Long id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Optional<User> retUser = Optional.empty();

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM users WHERE id = " + id);
            rs = stmt.executeQuery();
            if (rs != null) {
                rs.next();
                User user = new User();
                user.setId(id);
                user.setLogin(rs.getString(2));
                user.setPassword(rs.getString(3));
                retUser = Optional.of(user);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        return retUser;
    }

    private Optional<Chatroom> findRoomById(Long id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Optional<Chatroom> retRoom = Optional.empty();

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM chatroom WHERE id = " + id);
            rs = stmt.executeQuery();
            if (rs!= null) {
                rs.next();
                Chatroom chatroom = new Chatroom();
                chatroom.setId(id);
                chatroom.setName(rs.getString(2));
                retRoom = Optional.of(chatroom);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        return retRoom;
    }

}
