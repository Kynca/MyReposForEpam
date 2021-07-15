package task8;

public class BufferReaderThread implements Runnable {

    StringBuffer buffer;

    public BufferReaderThread(StringBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        synchronized (buffer) {
            while (Runner.counter < 3) {
                buffer.append("A");
                Runner.counter++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
