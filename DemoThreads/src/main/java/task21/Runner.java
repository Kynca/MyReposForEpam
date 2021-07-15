package task21;

import java.util.concurrent.Phaser;

public class Runner {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        phaser.register();
        for(int i = 0;i<3;i++){
            Thread thread = new Thread(new PhaserGoer(phaser));
            thread.start();
        }

        phaser.arriveAndAwaitAdvance();
        System.out.println("all threads reach 1st point");
        phaser.arriveAndAwaitAdvance();
        System.out.println("all threads reach 2nd point");
        phaser.arriveAndAwaitAdvance();
        System.out.println("all threads reach 3d point");
        phaser.arriveAndDeregister();
    }
}
