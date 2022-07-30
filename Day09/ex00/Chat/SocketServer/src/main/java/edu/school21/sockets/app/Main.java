package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        if (args.length != 1 || !args[0].startsWith("--port=")) {
            System.err.println("Specify the server port using --port=");
            System.exit(-1);
        }
        Integer port = Integer.parseInt(args[0].substring(7));
        Server server = new Server(port);
        server.start(port);
    }
}
