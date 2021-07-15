package task7;

import java.io.FileWriter;
import java.io.IOException;

public class CommonResource {
    FileWriter fileWriter;

    public CommonResource(String filename) throws IOException {
        fileWriter = new FileWriter(filename, true);
    }

    public synchronized void writing(String str, int i) {

        try {
            fileWriter.append(str + "tread ");
            Thread.sleep(100);
            fileWriter.append(" " + i + "time wrote ");
        } catch (IOException e) {
            System.out.println("File exception");
        } catch (InterruptedException e) {
            System.out.println("Thread exception");
        }
    }

    public void close(){
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
