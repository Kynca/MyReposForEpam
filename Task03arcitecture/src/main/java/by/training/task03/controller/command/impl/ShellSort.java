package by.training.task03.controller.command.impl;

import by.training.task03.bean.Array;
import by.training.task03.bean.Repository;
import by.training.task03.controller.command.Command;
import by.training.task03.service.ArrayService;
import by.training.task03.service.factory.ServiceFactory;

public class ShellSort implements Command {
    public String execute(String request) {
        ServiceFactory serviceFactory=ServiceFactory.getInstance();
        ArrayService arrayService=serviceFactory.getArrayService();
        Repository repository=Repository.getInstance();
        Array array=repository.getArray();

        arrayService.shellSort(array);
        String response="Array is sorted by shell sort\n" +
                "Result is"+array.toString()+
                "\n Also u can see the result in file Sorted.txt";
        return response;
    }
}
