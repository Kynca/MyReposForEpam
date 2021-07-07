package by.training.classes04.controller;

import by.training.classes04.controller.command.Command;
import by.training.classes04.controller.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Class with map which stores String keys and commands for implementation
 */
public class CommandProvider {
    static final Logger controllerLog = LogManager.getLogger("ControllerLog");
    private final Map<String, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put("SORT", new Sort());
        repository.put("CREATE_CLIENT", new CreateClient());
        repository.put("BILL_SUM", new BillSum());
        repository.put("CLIENT_SUM", new ClientBills());
        repository.put("SEARCH", new BillSearch());
        repository.put("SHOW_LIST", new ShowList());
        repository.put("INI", new Initialise());
    }

    /**
     * define which implementation controller wil use for chosen command
     *
     * @param name name of key in hashmap
     * @return defined implementation for interface
     */
    Command getCommand(String name) {
        String commandName;
        Command command = null;
        try {
            commandName = name.toUpperCase();
            controllerLog.info(commandName);
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            controllerLog.error("incorrect commandName input=" + name);
        }
        return command;
    }
}
