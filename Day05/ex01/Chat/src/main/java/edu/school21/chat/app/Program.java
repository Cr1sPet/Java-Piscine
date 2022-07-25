package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
import edu.school21.chat.models.Message;

public class Program {

    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASS = "";

    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();

        ds.setJdbcUrl(DB_URL);
        ds.setUsername(USER);
        ds.setPassword(PASS);

        MessagesRepository repository = new MessagesRepositoryImpl(ds);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID");

        if (scanner.hasNextLong()) {
            Long messageId = scanner.nextLong();
            Optional<Message> message = repository.findById(messageId);
            if (message.isPresent()) {
                System.out.println(message.get());
            } else {
                System.out.println("Message with id = " + messageId + " dont exist");
            }
        }
    }
}
