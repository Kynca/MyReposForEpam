package by.training.task03.controller.command.impl;

import by.training.task03.bean.Repository;
import by.training.task03.controller.command.Command;
import by.training.task03.service.MatrixService;
import by.training.task03.service.exception.ServiceException;
import by.training.task03.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MatrixCreator implements Command {

    static final Logger controllerLogger= LogManager.getLogger("ControllerLog");

    public String execute(String request) {

        Scanner scanner=new Scanner(System.in);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getMatrixService();
        Repository repository=Repository.getInstance();

        String data[]=request.split(" ");
        int columns=Integer.parseInt(data[1]);
        int rows = Integer.parseInt(data[2]);
        int position = Integer.parseInt(data[3]);
        String[] matrix=new String[columns*rows];
        for(int i=0;i<columns*rows;i++){
            matrix[i]=scanner.next();
        }
        String response;
        try {
            repository.addMatrix(matrixService.matrixCreator(matrix,columns,rows),position);
            response="matrix added";
        } catch (ServiceException e) {
            response=e.toString();
            controllerLogger.error(response);
        }
        return response;
    }
}
