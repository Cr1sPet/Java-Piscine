public class Program {

    public static Integer displayCount;


    public static void parseArgs(String[] args) {

        if (args.length != 1) {
            System.err.println("Error : invalid arguments length");
            System.exit(-1);
        }

        String[] inputArray = args[0].split("=");
        if (inputArray.length != 2) {
            System.err.println("Error : bad arguments");
            System.exit(-1);
        }

        try {
            displayCount = Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error : bad arguments");
            System.exit(-1);
        }

    }


    public static void main(String[] args) throws InterruptedException {

        parseArgs(args);

        Store store = new Store(displayCount);
        Producer producer = new Producer(store, displayCount);
        Consumer consumer = new Consumer(store, displayCount);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

    }
}
