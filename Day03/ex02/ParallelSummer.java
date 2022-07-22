import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelSummer {

    private int threadsCount;
    private int arrayLength;
    private int step;
    private int []arr;
    Result result = new Result();

    SummerThread[] summerThreads;

    public ParallelSummer(int array[], int threadsCount) {

        this.arr = array;
        this.threadsCount = threadsCount;
        arrayLength = array.length;

        step = computeStep();
        summerThreads = new SummerThread[threadsCount];
    }

    public int computeStep() {
        return  arrayLength / threadsCount;
    }


    public int getSum() throws InterruptedException {

        int from = 0;
        int to = 0;
        for (int i = 0; i < threadsCount - 1; i++) {

            from = step * i;
            to = from + step - 1;

            summerThreads[i] = new SummerThread(from, to, arr, result, i + 1);

        }

        if (arrayLength % threadsCount != 0) {
            step = arrayLength - (step * (threadsCount - 1));
        }
        if (threadsCount > 1) {
            summerThreads[summerThreads.length - 1] =
                    new SummerThread(to + 1, to + step, arr, result, summerThreads.length);
        } else {
            summerThreads[0] = new SummerThread(0, arrayLength - 1, arr, result, 1);
        }

        for (SummerThread thread : summerThreads) {
            thread.run();
        }

        for (SummerThread thread : summerThreads) {
            thread.join();
        }

        return result.resultValue;
    }


    public class Result {
        int resultValue = 0;
    }
}
