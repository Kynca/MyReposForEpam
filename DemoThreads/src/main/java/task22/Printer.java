package task22;

public class Printer implements Runnable {

    Incrementor incrementor;

    public Printer(Incrementor incrementor){
        this.incrementor = incrementor;
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(incrementor.getNumber() + " get " + i + " times "  + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
