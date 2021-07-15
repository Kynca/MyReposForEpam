package by.training.task06threads.service;

import by.training.task06threads.bean.MatrixStorage;
import by.training.task06threads.bean.ResourcePool;

import java.util.concurrent.TimeUnit;

public class PoolFill implements Runnable {

    MatrixStorage storage = MatrixStorage.getInstance();
    ResourcePool pool;
    int id;


    public PoolFill(ResourcePool pool, int id) {
        this.pool = pool;
        this.id = id;
    }


    @Override
    public void run() {
        try {
            while (true) {
                if (storage.getSize() > storage.getChanges()) {
                    Integer index = pool.getIndex();
                    if (index != null) {
                        storage.setElement(index, id);
                        pool.used();
                    }
                    TimeUnit.MILLISECONDS.sleep(1);
                }else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
