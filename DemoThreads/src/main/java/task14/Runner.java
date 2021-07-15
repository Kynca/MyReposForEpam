package task14;

import java.util.concurrent.Semaphore;

public class Runner {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(5);

        for(int i = 0; i<14; i++){
            Thread philosopher = new Thread(new Philosopher(sem),""+i);
            philosopher.start();
        }
    }
}
