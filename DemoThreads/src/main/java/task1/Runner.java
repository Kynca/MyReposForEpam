package task1;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

    private static final Logger rootLogger = LogManager.getRootLogger();

    public static void main(String[] args) {
        rootLogger.info("this is the main thread");
        DemoThread thread = new DemoThread();
        thread.start();

    }
}
