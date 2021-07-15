package task17;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Bid extends Thread {

    Random random = new Random();
    int price;
    CyclicBarrier barrier;
    long id;
    String result;

    public Bid(int startPrice, CyclicBarrier barrier, long id) {
        price = startPrice;
        this.barrier = barrier;
        this.id = id;
        result = "Client " + id + " lose and leave auction";
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(600));
            price += random.nextInt(1000);
            System.out.println("Client " + id + " bid is " + price);
            barrier.await();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void winner(){
        result = "Client " + id + " pay for win take prize and leave";
    }
}
