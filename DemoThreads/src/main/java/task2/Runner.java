package task2;

public class Runner {

    public static void main(String[] args) {
        RunnablePerson ya = new RunnablePerson("Ilya");
        RunnablePerson bob = new RunnablePerson("Bob");

        Thread t1 = new Thread(ya);
        Thread t2 = new Thread(bob);
        t1.setPriority(10);
        t2.setPriority(1);
//        t1.setPriority(Thread.MAX_PRIORITY);
//        t2.setPriority(Thread.MIN_PRIORITY);


        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + Thread.currentThread().getName() + " finished");
    }
}
