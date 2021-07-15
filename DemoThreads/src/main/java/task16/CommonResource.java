package task16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CommonResource {

    BlockingQueue<Integer> numbers = new ArrayBlockingQueue<>(5) {
        {
            this.offer(100);
            this.offer(99);
            this.offer(-1);
            this.offer(50);
            this.offer(900);
        }
    };

    public void take(long waitTime){
        try {
            Integer element = numbers.poll(waitTime, TimeUnit.MILLISECONDS);
            if (element != null) {
                System.out.println("taker " + Thread.currentThread().getName() + " take element " + element +" and queue size is " + numbers.size());
            } else{
                System.out.println("expected time is out Taker " + Thread.currentThread().getName() + " leave this party queue is empty");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put(long waitTime){
        try {

            if(numbers.offer(Integer.valueOf(Thread.currentThread().getName()),waitTime,TimeUnit.MILLISECONDS)){
                System.out.println("Putter "+ Thread.currentThread().getName() + " put "+ Thread.currentThread().getName()
                        + " in queue queue size is " + numbers.size());
            }else {
                System.out.println("Putter "+ Thread.currentThread().getName() + " cant put element, queue is full");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


