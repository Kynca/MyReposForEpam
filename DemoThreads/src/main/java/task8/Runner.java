package task8;

public class Runner {
    static int counter = 0;
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        Thread thread = new Thread(new BufferReaderThread(buffer));
        thread.start();
        try {
            Thread.sleep(10);
            while (counter<6){
                buffer.append("B");
                counter++;
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(buffer);
    }
}
