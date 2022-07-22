import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelSummer {

    private int threadsCount;
    private int arrayLength;
    private int step;
    private int []arr;
    Object mutex = new Object();

    private static int returnSum = 0;
    public ParallelSummer(int array[], int threadsCount) {

        this.arr = array;
        this.threadsCount = threadsCount;
        arrayLength = array.length;

        step = computeStep() - 1;

    }

    public int computeStep() {

        int temp = arrayLength;
        for (int i = 0; (++temp) % threadsCount != 0; i++) {
            i++;
        }
        return  temp / threadsCount;
    }


    public int findArraySizeInInterval(int array[], int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += array[i];
        }
        return sum;
    }

    public int getSum() throws ExecutionException, InterruptedException {

        List<Future> futureList = new ArrayList<>();

        ExecutorService service = Executors.newFixedThreadPool(threadsCount);

        for (int i = 0; i < arrayLength ; ) {
            int to = i + step;
            if (to >= arrayLength) {
                to = arrayLength - 1;
            }

            int finalTo = to;
            int finalI = i;

            Future future = service.submit(() -> {
                int sum = findArraySizeInInterval(arr, finalI, finalTo);
                synchronized (mutex) {
                    returnSum += sum;
                }
                System.out.printf("Thread-%s from %d to %d sum is %d\n",
                        Thread.currentThread().getName().split("-")[3], finalI, finalTo, sum);
            });
            futureList.add(future);
            i = ++i + step;
        }

        for (Future future : futureList) {
            future.get();
        }

        service.shutdown();

        return returnSum;
    }
}
