package task16;

import java.util.concurrent.TimeUnit;

public class Putter implements Runnable{

    CommonResource res;


    public Putter(CommonResource res) {
       this.res = res;
    }

    @Override
    public void run() {
        for(int i = 0;i<2;i++ ) {
            res.put(200);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
