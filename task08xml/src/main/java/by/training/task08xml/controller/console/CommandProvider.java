package by.training.task08xml.controller.console;

import by.training.task08xml.controller.console.impl.GetInfo;

import java.util.HashMap;

public class CommandProvider {
    private HashMap<String, Command> repository = new HashMap<>();

    public CommandProvider(){
        repository.put("PARSE", new GetInfo());
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
//            controllerLog.info(commandName);
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
//            controllerLog.error("incorrect commandName input=" + name);
        }
        return command;
    }
}
