
import java.util.Scanner;

public class Program {

    public static int calculateSum(int num) {

        int sum = 0;


        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
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

    public static boolean checkPrime (int num) {

        int limit = mySqrt(num);
        for (int i = 2; i < limit; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
        

    public static boolean checkCofeeRequest(int num) {
        return checkPrime(calculateSum(num));
    }

    public static void main(String[]args){

        Scanner in = new Scanner(System.in);

        int inputNum;

        if  (!in.hasNextInt()) {
            System.exit(-1);
        }
        inputNum = in.nextInt();

        int result = 0;

        while (inputNum != 42) {
            if (checkCofeeRequest(inputNum)) {
                result++;
            }
            inputNum = in.nextInt();
        }
        System.out.println("Count of coffee-request - " + result);
        in.close();
    }
}