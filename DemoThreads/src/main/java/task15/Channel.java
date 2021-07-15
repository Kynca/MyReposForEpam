package task15;

import java.util.concurrent.TimeUnit;

public class Channel {
    String channelName;

    public Channel(String channelName ){
        this.channelName = channelName;
    }

    public String getChannelName(){
        return channelName;
    }

    public void inUse(){
        try {
            System.out.println("Client " + Thread.currentThread().getName() + " is use channel " + channelName);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
