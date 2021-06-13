package by.training.task03.controller.command.impl;

import by.training.task03.bean.Matrix;
import by.training.task03.bean.Repository;
import by.training.task03.controller.command.Command;
import by.training.task03.service.MatrixService;
import by.training.task03.service.factory.ServiceFactory;

public class MatrixTransposition implements Command {
    public String execute(String request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getMatrixService();
        Repository repository = Repository.getInstance();

        Matrix result =new Matrix(matrixService.transposition(repository.getMatrix(0)));
        String response = "Result of transposition is:\n " + result;
        return response;
    }
}
