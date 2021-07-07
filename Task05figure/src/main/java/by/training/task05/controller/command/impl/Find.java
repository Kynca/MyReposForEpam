package by.training.task05.controller.command.impl;

import by.training.task05.bean.Cube;
import by.training.task05.service.Repository;
import by.training.task05.controller.command.Command;
import by.training.task05.service.find.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class Find implements Command {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    @Override
    public List execute(String request) {

        Repository repository = Repository.getInstance();
        List<Cube> list;
        try {
            switch (request.split(" ")[1]) {
                case "id":
                    int id = Integer.parseInt(request.split(" ")[2]);
                    FindById findById = new FindById(id);
                    list = repository.query(findById);
                    break;
                case "Name":
                    FindByName findByName = new FindByName(request.split(" ")[2]);
                    list = repository.query(findByName);
                    break;
                case "Plane":
                    FindByOnPlane findByOnPlane = new FindByOnPlane();
                    list = repository.query(findByOnPlane);
                    break;
                case "Size":
                    int size = Integer.parseInt(request.split(" ")[2]);
                    FindBySize findBySize = new FindBySize(size);
                    list = repository.query(findBySize);
                    break;
                case "SizeRange":
                    double minSize = Double.parseDouble(request.split(" ")[2]);
                    double maxSize = Double.parseDouble(request.split(" ")[3]);
                    FindBySizeRange findByRange = new FindBySizeRange(minSize,maxSize);
                    list = repository.query(findByRange);
                    break;
                default:
                    list = null;
                    controllerLog.info(request.split(" ")[1] + "is not an option");
            }
            return list;
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            controllerLog.error(e);
            return null;
        }
    }
}
