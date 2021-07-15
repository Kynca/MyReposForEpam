package task12;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Store {
    final int LIMIT = 10;
    int product = 0;
    ReentrantLock locker;
    Condition get;
    Condition put;

    public Store(ReentrantLock locker) {
        this.locker = locker;
        get = locker.newCondition();
        put = locker.newCondition();
    }


    public void get() {
        locker.lock();
        try {
            while (product < 1) {
                get.await();
            }
            product--;
            System.out.println("Покупатель приобрел товар ");
            put.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void put() {
        locker.lock();
        try {
            while (product >= 5) {
                put.await();
            }
            product++;
            System.out.println("Производитель произвел товар \n на складе " + product + " товаров");
            get.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}
