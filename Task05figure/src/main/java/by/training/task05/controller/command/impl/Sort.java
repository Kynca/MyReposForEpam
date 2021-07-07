package by.training.task05.controller.command.impl;

import by.training.task05.service.Repository;
import by.training.task05.controller.command.Command;
import by.training.task05.service.sort.CubeComparator;

import java.util.List;

public class Sort implements Command {
    @Override
    public List execute(String request) {
        Repository repository = Repository.getInstance();
        CubeComparator sort = new CubeComparator(request.split(" ")[1]);
        return repository.query(sort);
    }
}
