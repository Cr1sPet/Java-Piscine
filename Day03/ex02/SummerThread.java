
public class SummerThread extends Thread{

    private int from;
    private int to;
    private int[] array;
    private ParallelSummer.Result result;
    private int index;

    public SummerThread(int from, int to, int [] array, ParallelSummer.Result result, int index) {
        this.to = to;
        this.from = from;
        this.array = array;
        this.result = result;
        this.index = index;
    }

    public int findArraySizeInInterval() {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += array[i];
        }

        synchronized (result) {
            result.resultValue += sum;
        }

        System.out.printf("Thread %d: from %d to %d sum is %d\n",
                index, from, to, sum);

        return sum;
    }

    @Override
    public void run() {
        findArraySizeInInterval();
    }
}
