package edu.school21.printer.app;

import java.io.File;
import java.io.IOException;

import edu.school21.printer.logic.ImageViewer;

public class Program {

    public static char white;
    public static char black;

    public static void parseArguments(String []args) {
        boolean ok = true;
        if (args.length != 2 || args[0].length() != 1 || args[1].length() != 1) {
            ok = false;
        }
        if (ok == false) {
            System.err.println("Bad arguments");
            System.exit(-1);
        }
        white = args[0].charAt(0);
        black = args[1].charAt(0);
    }

    public static void main(String[] args) throws IOException {

        parseArguments(args);

        ImageViewer imageViewer = new ImageViewer();

        char [][] bmpInNumberArray = imageViewer.seeBMPImage(white, black);
        for (int i = 0; i < bmpInNumberArray.length; i++) {
            for(int j = 0; j < bmpInNumberArray[i].length; j++) {
                System.out.print(bmpInNumberArray[j][i]);
            }
            System.out.println();
        }
    }
}
