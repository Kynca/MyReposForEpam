package task18;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Player extends Thread {

    CountDownLatch countDown;
    int id;
    Random random = new Random();

    public Player(CountDownLatch countDown, int id) {
        this.countDown = countDown;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
                System.out.println("Player " + id +" take quest " + (i + 1) );
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                System.out.println("Player " + id + " complete quest " + (i + 1));
                countDown.countDown();

            }
            System.out.println("Player " + id + " complete all quests");
            countDown.await();
            System.out.println("Player " + id + " take his prize and have fun of it ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
