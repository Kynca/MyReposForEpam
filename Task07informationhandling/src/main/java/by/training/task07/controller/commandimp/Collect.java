package by.training.task07.controller.commandimp;

import by.training.task07.controller.Command;
import by.training.task07.service.ServiceAction;
import by.training.task07.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class Collect implements Command {
    @Override
    public List execute(String request) {
        List<String> string = new ArrayList<>();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ServiceAction action = serviceFactory.getAction();
        string.add(action.collectText());
        return string;
    }
}
