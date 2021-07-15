package task22;

public class Runner {
    public static void main(String[] args) {

        Incrementor inc = new Incrementor();
        Thread t = new Thread(inc);
        t.start();
        for(int i = 0;i<10;i++){
            Thread r = new Thread(new Printer(inc));
            r.start();
        }
    }
}
