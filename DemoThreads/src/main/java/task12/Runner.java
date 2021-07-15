package task12;

import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Store store = new Store(lock);
        Thread prod = new Thread(new Producer(store));
        Thread cons = new Thread(new Consumer(store));
        prod.start();
        cons.start();
    }

}
