import java.util.Scanner;

public class Program {

    public static Menu.Mode checkMode(String [] args) {
        if (args.length != 1) {
            System.err.println("Incorrect number of argumnets");
            System.exit(-1);
        }
        if ("--profile=dev".equals(args[0])) {
            return Menu.Mode.DEVELOPER;
        } else if ("--profile=prod".equals(args[0])) {
            return Menu.Mode.PRODUCTION;
        } else {
            System.err.println("Incorrect input");
            System.exit(-1);
        }
        return null;
    }

    public static void main(String[] args) {

        Menu.Mode mode = checkMode(args);

        Menu menu = new Menu(mode);

        menu.start();
    }

}
