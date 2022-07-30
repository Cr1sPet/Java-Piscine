package edu.school21.sockets;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    static PrintWriter printWriter;
    static BufferedReader bufferedReader;

    private static Socket clientSocket;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!");

        String input;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            clientSocket = new Socket("localhost", 3333);
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputFromServer;
            inputFromServer = bufferedReader.readLine();
            System.out.println(inputFromServer);
            while ((input = reader.readLine()) != null) {
                printWriter.println(input);
                inputFromServer = bufferedReader.readLine();
                System.out.println(inputFromServer);
                if (inputFromServer.equals("Successful!")) {
                    clientSocket.close();
                    bufferedReader.close();
                    printWriter.close();
                    System.exit(0);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
