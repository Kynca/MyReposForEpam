package by.training.task03.controller;

import by.training.task03.controller.command.Command;
import by.training.task03.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    static final Logger controllerLogger= LogManager.getLogger("ControllerLog");

    private final Map<String, Command> repository= new HashMap<>();
    CommandProvider(){
        repository.put("BUBBLE_SORT", new BubbleSort());
        repository.put("SHELL_SORT", new ShellSort());
        repository.put("INSERTION_SORT", new InsertionSort());
        repository.put("SHAKE_SORT", new ShakeSort());
        repository.put("CHOICE_SORT", new ChoiceSort());
        repository.put("MULTIPLICATION",new MatrixMultiplication());
        repository.put("TRANSPOSITION",new MatrixTransposition());
        repository.put("NUM_MULTIPLICATION",new MatrixMultiplicationNum());
        repository.put("SUM", new MatrixSum());
        repository.put("SUBTRACTION",new MatrixSubtraction());
        repository.put("HAND_INI",new MatrixCreator());
        repository.put("RANDOM_INI",new RandomInitialize());
        repository.put("CLEAR",new ClearMatrix());
        repository.put("WRONG_REQUEST",new WrongRequest());
        repository.put("ARRAY_INI",new ArrayIni());
        repository.put("MERGE_SORT",new MergeSort());
    }

    Command getCommand(String name){
        String commandName;
        Command command = null;
        try{
            commandName = name.toUpperCase();
            controllerLogger.info(commandName);
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            controllerLogger.error("there is no such command");
        }
        return command;
    }
}
