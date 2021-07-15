package by.training.task06threads.service;

import by.training.task06threads.bean.MatrixStorage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockFill implements Runnable {

    private ReentrantLock lock;
    private int id;
    MatrixStorage storage = MatrixStorage.getInstance();

    public LockFill(ReentrantLock lock, int id){
        this.id = id;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (true){
                TimeUnit.NANOSECONDS.sleep(10);
                lock.lock();
                if(storage.getChanges() >= storage.getSize()){
                    break;
                }
               storage.setElement(storage.getChanges(), id);
                lock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
