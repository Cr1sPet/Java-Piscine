package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import edu.school21.chat.models.Message;
import exceptions.NotSavedSubEntityException;

public class Program {

    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASS = "";

    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(DB_URL);
        ds.setUsername(USER);
        ds.setPassword(PASS);

        User creator = new User(3L, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(3L, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
        MessagesRepository messagesRepository = new MessagesRepositoryImpl(ds);
        try {
            messagesRepository.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        }

        creator.setId(10L);
//        message.setAuthor(creator);
        try {
            messagesRepository.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        }
        creator.setId(1L);
        room.setId(12L);

        try {
            messagesRepository.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        }

    }
}
