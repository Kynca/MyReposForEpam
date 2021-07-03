package by.training.classes04.controller.command.impl;

import by.training.classes04.bean.MessageManager;
import by.training.classes04.bean.Repository;
import by.training.classes04.controller.command.Command;
import by.training.classes04.service.impl.BankOperationsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation for sum bill on different requires, choice command BILL_SUM
 */
public class BillSum implements Command {

    static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    /**
     * Validate information which user entered. If data is correct calls service
     * function sumBills(int type) and saves the result on Repository
     * @param request have command name, and user's type of sum
     * @return boolean false if sum is not calculated, true if it is
     */
    @Override
    public boolean execute(String request) {
        Repository repository = Repository.getInstance();
        BankOperationsImpl operations = new BankOperationsImpl();
        try {
            int type = Integer.parseInt(request.split(" ")[1]);
            double result = operations.sumBills(type);
            repository.setResult(result);
            controllerLog.info("client choose " + type + " of sort. result=" + result);
            return true;
        } catch (NumberFormatException e) {
            controllerLog.error(e.toString());
            return false;
        }
    }
}
