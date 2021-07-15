package task15;

public class Client implements Runnable {

    ResourcePool pool;

    public Client(ResourcePool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        Channel channel = pool.getResource(300);
        if (channel != null) {
            channel.inUse();
            pool.returnResource(channel);
        }
    }
}
