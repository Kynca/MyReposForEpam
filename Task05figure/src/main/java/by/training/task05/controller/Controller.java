package by.training.task05.controller;

import by.training.task05.controller.command.Command;

import java.util.List;

/**
 * carries out all interaction with the service
 */
public class Controller {
    private final CommandProvider provider=new CommandProvider();
    private final char DELIMITER=' ';

    /**
     * execute chosen command and gives response for View about completed action
     * @param request user choice with information what should controller do with information and all required data
     * @return status of executed command
     */
    public List executeTask(String request){
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(DELIMITER));
        executionCommand = provider.getCommand(commandName);
        List response;
        response = executionCommand.execute(request);
        return response;
    }

}
