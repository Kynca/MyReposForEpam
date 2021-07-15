package task10;

public class Store {
    int product=0;

    public synchronized void put(){
        while(product>=5){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        System.out.println("Товар произведён и доставлен на склад\n На складе " + product + " товаров");
        notify();
    }

    public synchronized void get(){
        while (product<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product--;
        System.out.println("Покупатель приобрёл товар");
        notify();
    }
}
