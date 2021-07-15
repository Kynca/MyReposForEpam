package task5;

public class Runner {
    public static void main(String[] args) {
        ThreadToDisable threadToDisable = new ThreadToDisable();
        Thread thread = new Thread(threadToDisable," Thread to disable");
        thread.setDaemon(true);
        thread.start();


        try {
//            Thread.sleep(10); interrupt scenario
//            thread.interrupt();
//            System.out.println("thread interrupted");
            Thread.sleep(10000);
            System.out.println("main stopped");
//            threadToDisable.disable(); реалиазиция isActive
        } catch (InterruptedException e) {
        }


    }
}
