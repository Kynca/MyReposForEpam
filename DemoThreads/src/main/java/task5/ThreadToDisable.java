package task5;

import java.util.concurrent.TimeUnit;

public class ThreadToDisable implements Runnable{

    private boolean isRunning;

    public ThreadToDisable(){
        isRunning = true;
    }

    public void disable(){
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning){
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("thread is stopped");


//        while(!Thread.currentThread().isInterrupted()){ interrupt scenario
//            System.out.println(Thread.currentThread().getName() + " is running");
//        }
    }


}
