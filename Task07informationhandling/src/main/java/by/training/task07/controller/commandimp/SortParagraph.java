package by.training.task07.controller.commandimp;

import by.training.task07.controller.Command;
import by.training.task07.service.ServiceAction;
import by.training.task07.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class SortParagraph implements Command {
    @Override
    public List execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ServiceAction action = serviceFactory.getAction();
        List result = new ArrayList();
        result.addAll(action.sortParagraph());
        return result;
    }
}
