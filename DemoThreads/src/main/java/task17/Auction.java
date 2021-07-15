package task17;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Auction {
    private List<Bid> bidList = new ArrayList();
    CyclicBarrier barrier;
    int startPrice;
    Random random = new Random();

    public Auction() {
        startPrice = random.nextInt(500);
        System.out.println("started point is " + startPrice);
        barrier = new CyclicBarrier(5,new WinnerDefiner(bidList));
    }

    public void addBid(Bid bid) {
        bidList.add(bid);
    }

    public CyclicBarrier getBarrier(){
        return barrier;
    }

    public int getStartPrice() {
        return startPrice;
    }
}
