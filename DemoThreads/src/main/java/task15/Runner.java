package task15;

import java.util.LinkedList;
import java.util.Queue;

public class Runner {
    public static void main(String[] args) {
        Queue<Channel> channels = new LinkedList<>(){
            {
                this.add(new Channel("Radio"));
                this.add(new Channel("911"));
                this.add(new Channel("MomsCall"));
                this.add(new Channel("Comedy channel"));
                this.add(new Channel("Med Center"));
            }
        };

        ResourcePool resourcePool = new ResourcePool(channels);

        for(int i = 0;i<20;i++){
            Thread t = new Thread(new Client(resourcePool));
            t.start();
        }
    }

}
