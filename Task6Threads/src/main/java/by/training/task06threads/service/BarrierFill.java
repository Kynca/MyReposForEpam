package by.training.task06threads.service;

import by.training.task06threads.bean.MatrixStorage;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierFill implements Runnable {

    MatrixStorage storage = MatrixStorage.getInstance();
    int times;
    CyclicBarrier barrier;
    int id;

    public BarrierFill(CyclicBarrier barrier, int id) {
        this.id = id;
        this.barrier = barrier;
        times = storage.getSize() / storage.getNumberOfThreads();
        if (storage.getSize() % storage.getNumberOfThreads() > 0) {
            times++;
        }
    }

    @Override
    public void run() {
        int position;
        try {
            for (int i = 0; i < times; i++) {
                position = i * storage.getNumberOfThreads();
                position += barrier.await();
                if (storage.getSize() - position > 0) {
                    storage.setElement(position, id);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
