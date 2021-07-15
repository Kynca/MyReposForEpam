package by.training.task06threads.controller;

import by.training.task06threads.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");
    private final CommandProvider provider=new CommandProvider();
    private final char DELIMITER=' ';

    /**
     * execute chosen command and gives response for View about completed action
     * @param request user choice with information what should controller do with information and all required data
     * @return status of executed command
     */
    public void executeTask(String request){
        String commandName;
        Command executionCommand;
        commandName = request.substring(0, request.indexOf(DELIMITER));
        executionCommand = provider.getCommand(commandName);

        if(executionCommand == null){
            controllerLog.error("There in such commands");
            return;
        }

        executionCommand.execute(request);
    }
}
