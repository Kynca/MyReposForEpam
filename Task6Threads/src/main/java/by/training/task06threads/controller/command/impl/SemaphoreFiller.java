package by.training.task06threads.controller.command.impl;

import by.training.task06threads.bean.MatrixStorage;
import by.training.task06threads.controller.command.Command;
import by.training.task06threads.service.ServiceActionImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class SemaphoreFiller implements Command {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");
    private MatrixStorage storage = MatrixStorage.getInstance();

    @Override
    public void execute(String request) {

        ServiceActionImpl action = new ServiceActionImpl();
        try {
            action.semaphoreFill();
            while (storage.getChanges() < storage.getSize()) ;
            {
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (InterruptedException e) {
           controllerLog.error(e);
        }
    }
}
