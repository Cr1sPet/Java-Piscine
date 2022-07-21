import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

public class Program {

    public static final String PARAMETER = "--current-folder=";

    public static String parseArgs(String[] args) {

         if (args.length != 1) {
            System.err.println("Error : invalid arguments length");
            System.exit(-1);
        }

        if (!args[0].contains(PARAMETER) || args[0].length() < PARAMETER.length() + 1) {
            System.err.println("Error : bad argument");
            System.exit(-1);
        }
        return args[0].substring(PARAMETER.length());
    }

    public static void main(String[] args) {

        String startPath =  parseArgs(args);
        try {
            Menu menu = new Menu(startPath);
            menu.start();
        } catch (FileNotFoundException e) {
            System.out.println("Error : current folder dont exist");
        } catch (NotDirectoryException e) {
            System.out.println("Error : current file is not directory");
        } catch (IOException e) {
            System.out.println(e);
        } catch (NotAbsolutePathException e) {
            System.out.println("Error : current file is not absolute path");
        }

    }
}
