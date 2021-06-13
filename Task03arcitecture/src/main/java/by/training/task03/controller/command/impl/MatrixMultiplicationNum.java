package by.training.task03.controller.command.impl;

import by.training.task03.bean.Matrix;
import by.training.task03.bean.Repository;
import by.training.task03.controller.command.Command;
import by.training.task03.service.MatrixService;
import by.training.task03.service.factory.ServiceFactory;

public class MatrixMultiplicationNum implements Command {
    public String execute(String request){

        int number= Integer.parseInt(request.split(" ")[1]);

        ServiceFactory serviceFactory= ServiceFactory.getInstance();
        MatrixService matrixService= serviceFactory.getMatrixService();
        Repository repository=Repository.getInstance();

        Matrix result=new Matrix(matrixService.multiplicationOnNum(repository.getMatrix(0),number));
        String response="Result of transposition is:\n "+ result;
        return response;
    }
}
