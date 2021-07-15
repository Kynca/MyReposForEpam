package task14;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

    Semaphore sem;
    int num = 0;

    public Philosopher(Semaphore sem) {
        this.sem = sem;
    }

    @Override
    public void run() {
        while(num<3){
            try {
                System.out.println("филосов " + Thread.currentThread().getName()+ " хочет кушать");
                sem.acquire();
                System.out.println("филисов " + Thread.currentThread().getName() + " сел за стол и кушает");
                num++;
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("филосов " + Thread.currentThread().getName() + " покушал и встал из-за стола");
                if(num == 3){
                    System.out.println("филосов " + Thread.currentThread().getName() + "наелся на весь день");
                }
                sem.release();
                TimeUnit.MILLISECONDS.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
