package ex01;


import java.util.Scanner;

public class Program {


    public static void exitPrintResult(boolean isPrime, int iterationsNumber) {
        System.out.println(isPrime + " " + iterationsNumber);
        System.exit(0);
    }


    public static int mySqrt(int num) {

        int ret = 1;

        for (int i = 0; i < num; i++) {
            if (i * i >= num) {
                ret = i;
                break;
            }
        }

        return ret;
    }

    public static void checkPrime (int num) {

        int limit = mySqrt(num);
        int j = 0;
        for (int i = 2; i < limit; i++) {
            j++;
            if (num % i == 0) {
                exitPrintResult(false, j);
            }
        }
        j++;
        exitPrintResult(true, j);
    }

    public static void main(String[]args){

         
        Scanner in = new Scanner(System.in);
        
        int inputNum =  in.nextInt();

        if (inputNum <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        checkPrime(inputNum);

        in.close();

    }
}

