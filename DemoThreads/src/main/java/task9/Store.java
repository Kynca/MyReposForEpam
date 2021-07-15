package task9;

public class Store {
    int counter = 0;
    final static int LIMIT = 10;

    public synchronized int put() {
        if (counter < LIMIT) {
            ++counter;
            System.out.println("На складе " + counter + " товаров");
            return 1;
        }
        System.out.println("Склад полон");
        return 0;
    }

    public synchronized int get() {
        if (counter > 0) {
            --counter;
            System.out.println("На складе осталось " + counter + " товаров");
            return 1;
        }
        System.out.println("Склад пуст");
        return 0;
    }

    public synchronized int getCounter(){
        return counter;
    }
}
