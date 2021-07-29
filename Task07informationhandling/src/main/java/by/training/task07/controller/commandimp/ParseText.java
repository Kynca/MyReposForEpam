package by.training.task07.controller.commandimp;

import by.training.task07.controller.Command;
import by.training.task07.service.ServiceAction;
import by.training.task07.service.ServiceFactory;
import by.training.task07.service.exceptrion.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ParseText implements Command {
    @Override
    public List execute(String request) {

        List list;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ServiceAction action = serviceFactory.getAction();
        try {
            action.parseText(request.split("~")[1]);
            list = new ArrayList();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return list;
    }
}
