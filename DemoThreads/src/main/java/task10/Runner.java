package task10;

public class Runner {
    public static void main(String[] args) {
        Store store = new Store();
        Thread prod = new Thread(new Producer(store));
        Thread cons = new Thread(new Consumer(store));
        prod.start();
        cons.start();
    }
}
