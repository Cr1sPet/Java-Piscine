


import java.util.Scanner;

public class Program {

    public static void exitPrintResult(boolean isPrime, long iterationsNumber, Scanner in) {
        in.close();
        System.out.println(isPrime + " " + iterationsNumber);
        System.exit(0);
    }

    public static void checkPrime (long num, Scanner in) {

        long i = 2;

        for ( ; i * i <= num; i++) {
            if (num % i == 0) {
                exitPrintResult(false, i - 1, in);
            }
        }
        exitPrintResult(true, i - 1, in);
    }

    public static void main(String[]args){

        Scanner in = new Scanner(System.in);
        
        int inputNum =  in.nextInt();

        if (inputNum <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        checkPrime(inputNum, in);
        in.close();
    }
}

