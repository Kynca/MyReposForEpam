package task12;

public class Producer implements Runnable{

    Store store;

    public Producer(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        for(int i = 0; i<15;i++){
            store.put();
        }
    }
}
