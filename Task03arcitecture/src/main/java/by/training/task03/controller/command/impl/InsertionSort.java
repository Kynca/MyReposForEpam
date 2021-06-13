package by.training.task03.controller.command.impl;

import by.training.task03.bean.Array;
import by.training.task03.controller.command.Command;
import by.training.task03.service.ArrayService;
import by.training.task03.service.factory.ServiceFactory;

public class InsertionSort implements Command {
    public String execute(String request) {
        Array array = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ArrayService arrayService = serviceFactory.getArrayService();

        arrayService.insertionSort(array);
        String response = "Array is sorted by insertion sort\n" +
                "Result is" + array.toString() +
                "\n Also u can see the result in file Sorted.txt";
        return response;
    }
}
