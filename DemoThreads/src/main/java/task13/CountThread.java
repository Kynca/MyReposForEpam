package task13;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CountThread implements Runnable {

    CommonResource resource;
    Semaphore semaphore;

    public CountThread(CommonResource resource, Semaphore semaphore) {
        this.resource = resource;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " is waiting");
            semaphore.acquire();
            System.out.println("Thread " + Thread.currentThread().getName() + " in action");
            for (int i = 0; i < 5; i++) {
                resource.x++;
                System.out.println("counter "+resource.x);
                TimeUnit.MILLISECONDS.sleep(100);
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " finished action");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

}
