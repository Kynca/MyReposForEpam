package by.training.task05.controller.command.impl;

import by.training.task05.controller.command.Command;
import by.training.task05.service.exception.ServiceException;
import by.training.task05.service.storageservice.StorageServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FillStorage implements Command {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public List execute(String request) {
        StorageServiceImpl storageService = new StorageServiceImpl();
        try {
            storageService.fillStorage(request.split(" ")[1]);
        } catch (ServiceException e) {
           controllerLog.error(e);
        }
        return null;
    }
}
