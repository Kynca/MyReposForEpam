package by.training.task06threads.controller;

import by.training.task06threads.controller.command.impl.*;
import by.training.task06threads.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class CommandProvider {

    private static final Logger controllerLog = LogManager.getLogger("ControllerLog");
    private HashMap<String, Command> repository = new HashMap<>();

    public CommandProvider(){
        repository.put("INI",new MatrixIni());
        repository.put("BARRIER", new BarrierFiller());
        repository.put("SEMAPHORE", new SemaphoreFiller());
        repository.put("LOCK", new LockFiller());
        repository.put("POOL", new PoolFiller());
        repository.put("THREAD", new ThreadIni());
    }

    Command getCommand(String name) {
        String commandName;
        Command command = null;
        try {
            commandName = name.toUpperCase();
         controllerLog.info(commandName);
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
         controllerLog.error("command does not exist");
        }
        return command;
    }
}
