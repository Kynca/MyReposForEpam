package by.training.task06threads.controller.command.impl;

import by.training.task06threads.bean.MatrixStorage;
import by.training.task06threads.controller.command.Command;
import by.training.task06threads.service.ServiceActionImpl;
import by.training.task06threads.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixIni implements Command {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    public void execute(String request) {
        ServiceActionImpl serviceAction = new ServiceActionImpl();

        try {
            serviceAction.matrixInitialisation(request.split(" ")[1],request.split(" ")[2]);
        } catch (ServiceException e) {
            controllerLog.error(e);
        }

    }
}
