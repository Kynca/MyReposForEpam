package task11;

import java.util.Random;

public class WriteThread implements Runnable{

    CommonResource res;
    Random random = new Random();

    public WriteThread(CommonResource res){
        this.res = res;
    }

    @Override
    public void run() {
        for(int i =0; i<4; i++){
            res.put(random.nextInt(1000));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
