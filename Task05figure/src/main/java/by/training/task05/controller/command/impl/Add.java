package by.training.task05.controller.command.impl;

import by.training.task05.service.Repository;
import by.training.task05.controller.command.Command;
import by.training.task05.service.add.AddCube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Add implements Command {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public List execute(String request) {

        Repository repository = Repository.getInstance();
        AddCube addCube = null;
        String[] data = request.split(" ");
        double[][] cubePoints = new double[8][3];
        if (data.length < 25) {
            controllerLog.error("incorrect data, points lenght =" + data.length);
            return null;
        }

        try {
            int m = 0;
            int n;
            for (int j = 1; j < 25; j++) {
                n = (j-1) % 3;
                if (n == 0 && j != 1) {
                    m++;
                }
                cubePoints[m][n] = Double.parseDouble(data[j]);
            }

            if (data.length == 25) {
                addCube = new AddCube(cubePoints);
            } else if (data.length == 26) {
                addCube = new AddCube(cubePoints, data[25]);
            }

            if (addCube == null) {
                controllerLog.info("Cube not added");
                return null;
            }
            return repository.query(addCube);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            controllerLog.error(e);
            return null;
        }
    }
}
