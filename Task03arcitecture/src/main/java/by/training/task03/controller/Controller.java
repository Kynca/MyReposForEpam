package by.training.task03.controller;

import by.training.task03.controller.command.Command;

public class Controller {
private final CommandProvider provider=new CommandProvider();
private final char DELIMITER=' ';

    public String executeTask(String request){
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(DELIMITER));
        executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(request);
        return response;
    }

}
