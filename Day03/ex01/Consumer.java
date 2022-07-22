class Consumer implements Runnable {

    Store store;
    int displayCount;

    Consumer(Store store, int displayCount) {
        this.store = store;
        this.displayCount = displayCount;
    }
    public void run(){
        for (int i = 1; i < displayCount; i++) {
            store.get();
        }
    }
}
