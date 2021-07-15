package task22;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Incrementor implements Runnable{

    public AtomicInteger number = new AtomicInteger(0);

    @Override
    public void run() {
       for(int i =0; i<=5000;i+=3){
                number.incrementAndGet();
           try {
               TimeUnit.MILLISECONDS.sleep(20);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

    }

    public AtomicInteger getNumber() {
        return number;
    }
}
