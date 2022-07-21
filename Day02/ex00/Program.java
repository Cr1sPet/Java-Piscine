
import java.io.*;
import java.sql.NClob;
import java.util.HashMap;
import java.util.Map;

public class Program {

    public static void main(String[] args) {
        try {
            SignatureAnalyzer signatureAnalyzer = SignatureAnalyzer.getInstance();
            Menu menu = new Menu();
            menu.start();
            signatureAnalyzer.printConfig();
        } catch (FileNotFoundException e) {
            System.out.println("Error in open file :" + e);
        } catch (IOException e) {
            System.out.println("Error in input from file :" + e);
        }

    }
}
