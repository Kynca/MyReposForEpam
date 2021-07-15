package task15;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ResourcePool {
    static final int POLL_SIZE = 5;
    Semaphore semaphore = new Semaphore(POLL_SIZE, true);
    Queue<Channel> resource = new LinkedList<>();

    public ResourcePool(Queue<Channel> channels) {
        resource.addAll(channels);
    }

    public Channel getResource(long waitTime) {
        Channel channel;
        try {
            System.out.println("Client " + Thread.currentThread().getName() + " is trying to take channel");
            if (semaphore.tryAcquire(waitTime, TimeUnit.MILLISECONDS)) {
                System.out.println("Client " + Thread.currentThread().getName() + " took channel");
                channel = resource.poll();
                return channel;
            }
            System.out.println("Client " + Thread.currentThread().getName() + " didn't get the channel and leave");
            return null;
        } catch (InterruptedException e) {
            System.out.println("something terrible was happened");
            return null;
        }
    }

    public void returnResource(Channel channel) {
        System.out.println("Client " + Thread.currentThread().getName() + " return " + channel.getChannelName());
        resource.add(channel);
        semaphore.release();
    }

}
