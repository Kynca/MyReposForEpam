package by.training.classes04.controller.command.impl;

import by.training.classes04.bean.Bill;
import by.training.classes04.controller.command.Command;
import by.training.classes04.service.impl.BankOperationsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation for sorting bill in defined order, choice command SORT
 */
public class Sort implements Command {

    static final Logger controllerLog= LogManager.getLogger("ControllerLog");

    /**
     * Validate information and calls service function sort and printing sorted Bills on console
     * @param request have command name and type of sort
     * @return true if sorted, false if not
     */
    public boolean execute(String request) {

        BankOperationsImpl operations = new BankOperationsImpl();

        try {
            for (Bill bills : operations.sort(Integer.parseInt(request.split(" ")[1]))) {
                System.out.println(bills.toString());
            }
            return true;
        }catch (NumberFormatException e){
            controllerLog.error(e);
            return false;
        }
    }
}
