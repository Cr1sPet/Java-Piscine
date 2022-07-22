class Producer implements Runnable {

    Store store;
    int displayCount;

    Producer(Store store, int displayCount) {
        this.store = store;
        this.displayCount = displayCount;
    }
    public void run(){
        for (int i = 0; i < displayCount; i++) {
            store.put();
        }
    }
}
