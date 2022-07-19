
public class Program {

    public static final int BASE = 10;

    public static int calculateSum(int number) {

        int sum = 0;

        while (number > 0) {
            sum += number % BASE;
            number /= BASE;
        }
        return sum;
    }

    public static void main(String[]args){

        int number = 479598;

        int ret = calculateSum(number);

        System.out.println(ret);
    }
}

