package task9;

public class Consumer implements Runnable {

    Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            int i = 1;
            while (i < 10) {
                if(store.get()==1) {
                    System.out.println("Покуптаель приобрел товар");
                    i++;
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
