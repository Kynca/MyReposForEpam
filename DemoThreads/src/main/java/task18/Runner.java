package task18;

import java.util.concurrent.CountDownLatch;

public class Runner {
    public static void main(String[] args) {
        CountDownLatch countDown = new CountDownLatch(15);
        for (int i = 0; i < 3; i++){
            Player player = new Player(countDown,(i+1));
            player.start();
        }
    }
}
