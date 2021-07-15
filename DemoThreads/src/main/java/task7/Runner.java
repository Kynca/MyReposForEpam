package task7;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        CommonResource resource = null;

            try {
                resource = new CommonResource("data/commonFileWriting.txt");
                Thread t1 = new Thread(new WritingThread(resource, "First"));
                Thread t2 = new Thread(new WritingThread(resource, "Second"));
                t1.start();
                t2.start();
                t1.join();
                t2.join();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                resource.close();
            }
    }

}
