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

        MessagesRepository messagesRepository = new MessagesRepositoryImpl(ds);
        Optional<Message> messageOptional = messagesRepository.findById(3L);
        Optional<Message> messageOptional1 = messagesRepository.findById(1L);

        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            System.out.println(message);
            if (messageOptional1.isPresent()) {
                message.setAuthor(messageOptional1.get().getAuthor());
            }
            message.setText("Bye");
            message.setDate(null);
            messagesRepository.update(message);
            System.out.println(message);
        } else {
            System.out.println("Cannot find message by ID");
        }

        System.out.println("#######################");

        Optional<Message> messageOptionalUser4 = messagesRepository.findById(4L);
        if (messageOptionalUser4.isPresent()) {
            Message message = messageOptionalUser4.get();
            message.setAuthor(new User(1000L, "123", "123", null, null));
            messagesRepository.update(message);
        } else {
            System.out.println("Cannot find message by ID");
        }

    }
}
