package ex03;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Program {

    public static final int MAX_WEEKS = 18;


    public static long addNumber(Scanner in1, long holder) {

//        Scanner in1 = new Scanner(System.in);

        int inputNum = in1.nextInt();
        int min = inputNum;
        int temp;


        for (int i = 0; i < 4; i++) {
            
            temp = in1.nextInt();

            if (temp < min) {
                min = temp;
            }

        }

        holder = holder * 10 + min;

        return holder;

    }

    public static void printResult(long holder, int weekNumber) {

        if (holder == 0) {

        } else {
            printResult(holder / 10, weekNumber - 1);

            int minRes = (int) (holder % 10) ;

            System.out.print("Week" + (weekNumber - 1) + " ");
            for (int i = 0; i < minRes; i++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
        
    }


    public static void exitWithError(Scanner in) {
        in.close();
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    public static String readConsole(Scanner in, int currentWeekNumber) {
        String firstVal = in.next();
        if ("Week".equals(firstVal)) {
            if (currentWeekNumber != in.nextInt()) {
                exitWithError(in);
            }
            return "Week";
        }

        if ("42".equals(firstVal)) {
            return "42";
        }
        exitWithError(in);
        return "";
    }

    public static void main(String[]args)  {

        Scanner in = new Scanner(System.in);

        int weakNumber = 1;
        long holder = 0;

        String input = readConsole(in, weakNumber);

        while (!"42".equals(input) && weakNumber <= MAX_WEEKS) {
            holder = addNumber(in, holder);
            weakNumber++;
            input = readConsole(in, weakNumber);
        }

        printResult(holder, weakNumber);

        in.close();

    }
}

