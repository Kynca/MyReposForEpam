package by.training.classes04.controller.command.impl;

import by.training.classes04.bean.Repository;
import by.training.classes04.controller.command.Command;
import by.training.classes04.service.impl.BankOperationsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation for calculating sum of unblocked users bills , choice command CLIENT_SUM
 */
public class ClientBills implements Command {

    static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    /**
     * calls the function sumClientBill(String passId) if sum is calculated saves result
     * on repository
     * @param request have name of command and client passportId
     * @return boolean true if client exist and sum is calculated, otherwise false
     */
    @Override
    public boolean execute(String request) {
        Repository repository = Repository.getInstance();
        BankOperationsImpl operations = new BankOperationsImpl();
        try {
            double result = operations.sumClientBill(request.split(" ")[1]);
            if (result == Integer.MIN_VALUE) {
                controllerLog.info("Client with passId=" + request.split(" ")[1] + "not found");
                return false;
            } else {
                repository.setResult(result);
                controllerLog.info("result of searching sum passId=" + request.split(" ")[1] + "is " + result);
                return true;
            }
        }catch (NumberFormatException e){
            controllerLog.error(e);
            return false;
        }
    }
}
