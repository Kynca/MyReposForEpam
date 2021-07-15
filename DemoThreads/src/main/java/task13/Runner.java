package task13;

import java.util.concurrent.Semaphore;

public class Runner {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();

        for(int i=0;i<5;i++){
            Thread t = new Thread(new CountThread(res,sem)," "+i);
            t.start();
        }
    }

}
