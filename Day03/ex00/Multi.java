package ex00;

public class Multi  extends Thread {

    int count = 0;

    Multi(String name, int count) {
        super(name);
        this.count = count;
    }

    @Override
    public void run(){

        for (int i = 0; i < count; i++) {
            System.out.println(getName());

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }

        }
    }
}