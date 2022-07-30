package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;

import java.io.*;
import java.net.Socket;

public class Session {

    private UsersService service;
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Session(Socket socket, UsersService service) throws IOException {

        this.socket = socket;
        this.service = service;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }


    public  String readCommand() throws IOException {
        String command = bufferedReader.readLine();
        while (!command.equals("signUp")) {
            command = bufferedReader.readLine();
            System.out.println("Read message : " + command);
        }
        return command;
    }

    public void signUp() throws IOException {
        readCommand();
        printWriter.println("Enter username:");
        String name = bufferedReader.readLine();
        printWriter.println("Enter password:");
        String password = bufferedReader.readLine();
        if (service.signUp(name, password)) {
            printWriter.println("Successful!");
            if (!socket.isClosed()) {
                socket.close();
            }
            printWriter.close();
            bufferedReader.close();
            System.out.println("GoodBye!");
            System.exit(0);
        } else {
            printWriter.println("Unsuccessful!");
        }
    }

    public void start() throws IOException {
        printWriter.println("Hello from Server!");

        while(true) {
            signUp();
        }

    }

}
