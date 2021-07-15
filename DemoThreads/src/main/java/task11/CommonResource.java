package task11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CommonResource {
    int x = 0;
    List<Integer> list = new ArrayList<>();
    Lock readLock;
    Lock writeLock;

    public CommonResource(ReentrantReadWriteLock locker) {
        list.add(182);
        list.add(306);
        list.add(100321);
        list.add(-1031);
        readLock = locker.readLock();
        writeLock = locker.writeLock();
    }

    public void readAll() {
        try {
            readLock.lock();
            int i = 0;
            for (Integer number:list) {
                System.out.println(++i + " number is " + number);
            }
            System.out.println(Thread.currentThread().getName() + " read list");
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void put(int data) {
        try {
            writeLock.lock();
            list.add(data);
            System.out.println(Thread.currentThread().getName() + " is wrote data");
        } finally {
            writeLock.unlock();
        }
    }
}
