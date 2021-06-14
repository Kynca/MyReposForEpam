package by.training.task03.controller.command.impl;

import by.training.task03.bean.Array;
import by.training.task03.bean.Repository;
import by.training.task03.controller.command.Command;
import by.training.task03.service.ArrayService;
import by.training.task03.service.factory.ServiceFactory;

public class MergeSort implements Command {
    @Override
    public String execute(String request) {
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        ArrayService arrayService=serviceFactory.getArrayService();
        Repository repository=Repository.getInstance();
        Array array=repository.getArray();

        arrayService.mergeSort(array,0,array.getLenght()-1);
        String response=" Array sorted by merge sort"+array.toString();
        return response;
    }
}
