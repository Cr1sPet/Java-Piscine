package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Integer port;
    private UsersService service;
    private Socket socket;
    BufferedReader bis;
    PrintWriter out;
    String name;

    public Server() {

    }

    public Server(Integer port) {

        this.port = port;
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.sockets");
        this.service = context.getBean("usersService", UsersService.class);
    }


    public void start(Integer port) {

        try (ServerSocket server = new ServerSocket(port)) {
            Session session = new Session(server.accept(), service);
            System.out.println("Client accepted");
            session.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        service.signUp("serj", "my_password");

    }


}
