package by.training.classes04.controller;


import by.training.classes04.controller.command.Command;
/**
 * carries out all interaction with the service
 */
public class Controller {
    private final CommandProvider provider = new CommandProvider();
    private final char DELIMITER = ' ';
    /**
     * execute chosen command and gives response for View about completed action
     * @param request user choice with information what should controller do with information and all required data
     * @return status of executed command
     */
    public boolean executeTask(String request) {
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(DELIMITER));
        executionCommand = provider.getCommand(commandName);
        boolean response;
        response = executionCommand.execute(request);
        return response;
    }
}
