package task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoThread extends Thread {

    private static final Logger rootLogger = LogManager.getRootLogger();

    @Override
    public void run() {
        System.out.println("Hello world");
        rootLogger.info("Thread running");
    }

}
