package by.training.task05.controller.command.impl;

import by.training.task05.service.Repository;
import by.training.task05.controller.command.Command;
import by.training.task05.service.remove.RemoveById;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Remove implements Command {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public List execute(String request) {
        Repository repository = Repository.getInstance();
        try {
            long id = Integer.parseInt(request.split(" ")[1]);
            RemoveById remove = new RemoveById(id);
            return repository.query(remove);
        } catch (NumberFormatException e) {
            controllerLog.error("id is not long" + e);
            return null;
        }
    }
}
