package task11;


import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Runner {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        CommonResource commonResource = new CommonResource(rwl);
        ReentrantLock lock = new ReentrantLock();

//        for (int i = 1; i < 6; i++){
//            Thread t = new Thread(new CountThread(commonResource,lock));
//            t.setName("Thread "+ i);
//            t.start();
//        }

        for (int i = 0; i < 3; i++) {
            Thread read = new Thread(new ReadThread(commonResource));
            read.setName("ReadThread "+ i);
            read.start();
        }
        for (int i = 0; i < 2; i++) {
         Thread write = new Thread(new WriteThread(commonResource));
            write.setName("WriteThread "+ i);
         write.start();
        }
    }
}
