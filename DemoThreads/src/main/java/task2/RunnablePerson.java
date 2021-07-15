package task2;

import java.util.concurrent.TimeUnit;

public class RunnablePerson extends Person implements Runnable{

    public RunnablePerson(String surname) {
        super(surname);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i+")"+getSurname() + " im running");

            try {

                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
