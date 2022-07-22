public class Store {
    private int displayCount;
    private int product=0;

    public Store(int displayCount) {
        this.displayCount = displayCount;
    }

    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("Hen");
        notify();
    }
    public synchronized void put() {
        while (product > 0) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("Egg");
        notify();
    }
}