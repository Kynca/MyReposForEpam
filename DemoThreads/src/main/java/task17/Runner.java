package task17;

public class Runner {
    public static void main(String[] args) {
        Auction auction = new Auction();
        for (int i = 0; i < 5; i++) {
            Bid bid = new Bid(auction.getStartPrice(), auction.getBarrier(),i);
            auction.addBid(bid);
            bid.start();
        }
    }
}
