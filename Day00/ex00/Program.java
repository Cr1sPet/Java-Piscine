
package ex00;

public class Program {


    public static int calculateSum(int num) {

        int sum = 0;


        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[]args){

        int num = 479598;

        int ret = calculateSum(num);

        System.out.println(ret);

    }
}

