package by.training.task06threads.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ResourcePool {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    Queue<Integer> queue = new LinkedList();
    Semaphore semaphore = new Semaphore(1,true);

    public ResourcePool(int size) {
        for (int i = 0; i < size; i++){
            queue.add(i);
        }
    }

    public Integer getIndex(){
        Integer result;
        try {
            if(semaphore.tryAcquire(10, TimeUnit.MILLISECONDS)) {
                result = queue.poll();
                debugLog.debug("que size is " +queue.size());
                return result;
            }
        } catch (InterruptedException e) {
            return null;
        }
        return null;
    }

    public void used(){
        semaphore.release();
    }

}
