package task21;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserGoer implements Runnable{

    Phaser phaser;
    Random random = new Random();

    public PhaserGoer(Phaser phaser){
        this.phaser = phaser;
        phaser.register();
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(200));
            System.out.println("reach 1st point " + Thread.currentThread().getName());
            phaser.arriveAndAwaitAdvance();
            TimeUnit.MILLISECONDS.sleep(random.nextInt(200));
            System.out.println("reach 2nd point " + Thread.currentThread().getName());
            phaser.arriveAndAwaitAdvance();
            TimeUnit.MILLISECONDS.sleep(random.nextInt(200));
            System.out.println("reach 3d point " + Thread.currentThread().getName());
            phaser.arriveAndDeregister();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
