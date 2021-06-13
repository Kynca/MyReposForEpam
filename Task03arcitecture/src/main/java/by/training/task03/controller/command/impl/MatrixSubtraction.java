package by.training.task03.controller.command.impl;

import by.training.task03.bean.Matrix;
import by.training.task03.bean.Repository;
import by.training.task03.controller.command.Command;
import by.training.task03.service.MatrixService;
import by.training.task03.service.exception.ServiceException;
import by.training.task03.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatrixSubtraction implements Command {

    static final Logger controllerLogger= LogManager.getLogger("ControllerLog");

    public String execute(String request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getMatrixService();
        Repository repository = Repository.getInstance();
        String response;
        Matrix result;
        try {
            result = new Matrix(matrixService.matrixSubtraction(repository.getMatrix(0), repository.getMatrix(1)));
            response = "Result of matrix subtraction is:\n" + result;
        } catch (ServiceException e) {
            response = e.toString();
            controllerLogger.error(response);
        }
        return response;
    }

}
