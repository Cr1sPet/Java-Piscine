
import static java.lang.Thread.currentThread;

public class Program {

    public static Integer displayCount;


    public static void parseArgs(String[] args) {

        if (args.length != 1) {
            System.err.println("Error : invalid arguments length");
            System.exit(-1);
        }

        String[] inputArray = args[0].split("=");
        if (inputArray.length != 2) {
            System.err.println("Error : bad arguments");
            System.exit(-1);
        }

        try {
            displayCount = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error : bad arguments");
            System.exit(-1);
        }

    }

    public static void main(String[] args) {

        parseArgs(args);


        MyThread egg = new MyThread("Egg", displayCount);
        MyThread henn = new MyThread("Henn", displayCount);

        egg.start();
        henn.start();

        try {
            egg.join();
            henn.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        Thread.currentThread().setName("Human");
        for(int i = 0; i < 50; i++) {
            System.out.println(currentThread().getName());
        }

    }

}
