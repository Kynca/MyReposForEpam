package task17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WinnerDefiner implements Runnable{

    private List<Bid> bidList = new ArrayList<>();

    public WinnerDefiner(List<Bid> bidList){
        this.bidList = bidList;
    }

    @Override
    public void run() {
      Bid winner =  defineWinner();
        System.out.println("Winner is client " + winner.getId());
    }

    private Bid defineWinner(){
     Bid winner = Collections.max(bidList, new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
     winner.winner();
     return winner;
    }

}
