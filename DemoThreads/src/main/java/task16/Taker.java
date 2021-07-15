package task16;


import java.util.concurrent.TimeUnit;

public class Taker implements Runnable {

    CommonResource res;

    public Taker(CommonResource res) {
        this.res = res;
    }

    @Override
    public void run() {
        for(int i=0;i<2;i++) {
            res.take(200);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
