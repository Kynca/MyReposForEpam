package by.training.task05.controller.command.impl;

import by.training.task05.controller.command.Command;
import by.training.task05.service.Repository;
import by.training.task05.service.exception.ServiceException;
import by.training.task05.service.update.UpdateCube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Update implements Command {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public List execute(String request) {
        Repository repository = Repository.getInstance();
        String[] data = request.split(" ");
        String[] intArray = data[1].split(",");
        String[] doubleArray = data[2].split(",");

        try {
            int[] ints = new int[intArray.length];
            for (int i = 0; i < intArray.length; i++) {
                ints[i] = Integer.parseInt(intArray[i]);
            }

            double[] doubles = new double[doubleArray.length];
            for (int i= 0; i < doubleArray.length ;i++){
                doubles[i] = Double.parseDouble(doubleArray[i]);
            }
            UpdateCube update = new UpdateCube(doubles,ints, repository.getTempCube());
           return repository.query(update);
        } catch (NumberFormatException e) {
            controllerLog.error(e);
            return null;
        }
    }
}
