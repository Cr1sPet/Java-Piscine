import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Program {

    public static Integer arraySize;
    public static Integer threadsCount;

    public static Integer MAX_VALUE = 1000;
    public static Integer MIN_VALUE = -1000;


    public static final String PARAMETER_1 = "--arraySize";
    public static final String PARAMETER_2 = "--threadsCount";

    public static int checkParameters(String parameter, String input) {

        String[] inputArray = input.split("=");
        if (inputArray.length != 2 || !inputArray[0].equals(parameter)) {
            System.err.println("Error : bad arguments");
            System.exit(-1);
        }
        try {
            return Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error : bad arguments");
            System.exit(-1);
        }
        return -1;
    }

    public static void parseArgs(String[] args) {

        if (args.length != 2) {
            System.err.println("Error : invalid arguments length");
            System.exit(-1);
        }
        arraySize = checkParameters(PARAMETER_1, args[0]);
        threadsCount = checkParameters(PARAMETER_2, args[1]);

    }


    public static int[] generateArray() {
        int [] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = new Random().ints(MIN_VALUE, MAX_VALUE)
                    .findFirst()
                    .getAsInt();
        }
        return array;
    }

    public static int simpleSum(int []array) {
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        parseArgs(args);

        int [] array = generateArray();

        int sum = simpleSum(array);

        System.out.printf("Sum: %d\n", sum);

        ParallelSummer ps = new ParallelSummer(array, threadsCount);

        sum = ps.getSum();

        System.out.println("Sum by threads: " + sum);

    }

}
