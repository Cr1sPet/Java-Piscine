package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import edu.school21.printer.logic.ImageViewer;

import java.io.IOException;

@Parameters(separators = "=")
public class Program {

    @Parameter(names={"--white"}, required = true)
    static String white;

    @Parameter(names={"--black"},  required = true)
    static String black;


    public static void parse(String []argv) {
        Program program = new Program();
        try {
            JCommander.newBuilder()
                    .addObject(program)
                    .build()
                    .parse(argv);
        } catch (ParameterException e) {
            System.err.print("Parsing error : ");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
    public static void main(String[] argv) throws IOException {

        parse(argv);
        new ImageViewer().seeBMPImage(white, black);

    }
}
