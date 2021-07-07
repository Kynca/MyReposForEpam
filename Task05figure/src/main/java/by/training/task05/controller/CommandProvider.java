package by.training.task05.controller;

import by.training.task05.controller.command.Command;
import by.training.task05.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Class with map which stores String keys and commands for implementation
 */
public class CommandProvider {
    static final Logger controllerLogger = LogManager.getLogger("ControllerLog");

    private final Map<String, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put("FILL", new FillStorage());
        repository.put("ADD", new Add());
        repository.put("REMOVE", new Remove());
        repository.put("FIND", new Find());
        repository.put("SORT", new Sort());
        repository.put("SAVE", new Save());
        repository.put("UPDATE", new Update());
    }

    /**
     * define which implementation controller wil use for chosen command
     * @param name name of key in hashmap
     * @return defined implementation for interface
     */
    Command getCommand(String name) {
        String commandName;
        Command command = null;
        try {
            commandName = name.toUpperCase();
            controllerLogger.info(commandName);
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            controllerLogger.error("command does not exist");
        }
        return command;
    }
}
