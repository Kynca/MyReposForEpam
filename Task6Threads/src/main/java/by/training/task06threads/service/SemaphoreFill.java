package by.training.task06threads.service;

import by.training.task06threads.bean.MatrixStorage;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreFill implements Runnable{

    Semaphore semaphore;
    int id;

    public SemaphoreFill(Semaphore semaphore, int id) {
        this.semaphore = semaphore;
        this.id = id;
    }

    @Override
    public void run() {
        MatrixStorage storage = MatrixStorage.getInstance();
        try {
            while (true) {
            semaphore.acquire();

                if(storage.getChanges() >= storage.getSize()){
                    semaphore.release();
                    break;
                }

            storage.setElement(storage.getChanges(), id);
            semaphore.release();
            TimeUnit.MILLISECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
