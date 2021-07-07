package by.training.classes04.controller.command.impl;

import by.training.classes04.bean.Bill;
import by.training.classes04.bean.Repository;
import by.training.classes04.controller.command.Command;
import by.training.classes04.service.impl.BankOperationsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation for searching bill, choice command SEARCH
 */
public class BillSearch implements Command {

    static final Logger controllerLog= LogManager.getLogger("ControllerLog");

    /**
     * Find Bill on number which user entered. Validate data and if data is incorrect return false,
     * otherwise calls service function searchBill(int billNum)
     * If Bill exist saves this Bill in repository for next user choice
     * @param request consist name of the command and billNum in Sting format
     * @return boolean true if bill find and false if not
     */
    @Override
    public boolean execute(String request) {
        Repository repository = Repository.getInstance();
        BankOperationsImpl operations = new BankOperationsImpl();
        try {
            Bill bill = operations.searchBill(Integer.parseInt(request.split(" ")[1]));
            if (bill == null) {
                controllerLog.info("bill with num " + Integer.parseInt(request.split(" ")[1]) + " not found");
                return false;
            } else {
                repository.setBill(bill);
                return true;
            }
        }catch (NumberFormatException e){
            controllerLog.error(e);
            return false;
        }
    }
}
