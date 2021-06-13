package by.training.task03.controller.command.impl;

import by.training.task03.bean.Repository;
import by.training.task03.controller.command.Command;


public class ClearMatrix implements Command {

    public String execute(String request) {
        Repository repository=Repository.getInstance();

        repository.removeALl(Integer.parseInt(request.split(" ")[1]));
        String response="saved matrix was deleted";
        return response;
    }

}
