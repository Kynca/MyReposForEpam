package by.training.task03.controller.command.impl;

import by.training.task03.bean.Repository;
import by.training.task03.controller.command.Command;
import by.training.task03.service.MatrixService;
import by.training.task03.service.exception.ServiceException;
import by.training.task03.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RandomInitialize implements Command {

    static final Logger controllerLogger = LogManager.getLogger("ControllerLog");

    public String execute(String request) {
        String data[] = request.split(" ");
        int columns = Integer.parseInt(data[1]);
        int rows = Integer.parseInt(data[2]);
        int minValue = Integer.parseInt(data[3]);
        int maxValue = Integer.parseInt(data[4]);
        int position = Integer.parseInt(data[5]);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getMatrixService();
        Repository repository = Repository.getInstance();
        String response;
        try {
            repository.addMatrix(matrixService.randomIni(columns, rows, minValue, maxValue), position);
            response = "Matrix with random values was added";
        } catch (ServiceException e) {
            response = e.toString();
            controllerLogger.error(response);
        }
        return response;
    }
}
