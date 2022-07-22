package ex00;

public class MyThread  extends Thread {

    int count = 0;

    MyThread(String name, int count) {
        super(name);
        this.count = count;
    }

    @Override
    public void run(){

        for (int i = 0; i < count; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}