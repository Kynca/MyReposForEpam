package task11;

public class ReadThread implements Runnable {
    CommonResource res;

    public ReadThread(CommonResource res) {
        this.res = res;
    }

    @Override
    public void run() {

        for (int i = 0; i < 4; i++) {
            res.readAll();
        }
    }
}
