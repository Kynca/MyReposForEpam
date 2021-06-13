package by.training.task03.controller.command.impl;

import by.training.task03.controller.command.Command;
import by.training.task03.service.ArrayService;
import by.training.task03.service.exception.ServiceException;
import by.training.task03.service.factory.ServiceFactory;

public class ArrayIni implements Command {

    public String execute(String request) {
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        ArrayService arrayService=serviceFactory.getArrayService();
        try {
            arrayService.arrayIni();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return " ";
    }
}
