package by.training.classes04.controller.command.impl;

import by.training.classes04.controller.command.Command;
import by.training.classes04.service.impl.BankOperationsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation for filling Bank with data, choice command INI
 */
public class Initialise implements Command {

    static final Logger controllerLog= LogManager.getLogger("ControllerLog");

    /**
     * Fill Bank with all information about client and bills
     * @param request have name of command
     * @return false if file file is chosen and have incorrect data for Bank, true if not
     */
    @Override
    public boolean execute(String request) {

        BankOperationsImpl operations=new BankOperationsImpl();
        try {
            operations.initialise();
        }catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            controllerLog.info("file have incorrect data or damaged");
            return false;
        }
        return true;
    }
}
