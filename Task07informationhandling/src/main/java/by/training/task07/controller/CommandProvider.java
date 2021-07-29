package by.training.task07.controller;

import by.training.task07.controller.commandimp.*;
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
        repository.put("PARSE_TEXT", new ParseText());
        repository.put("COLLECT", new Collect());
        repository.put("LEXEME_SORT", new SortLexemes());
        repository.put("PAR_SORT", new SortParagraph());
        repository.put("WORD_SORT", new SortWords());
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
