package task9;

public class Producer implements Runnable{

    Store store;

    public Producer(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        while (store.getCounter()<10){
            try {
                store.put();
                Thread.sleep(75);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
