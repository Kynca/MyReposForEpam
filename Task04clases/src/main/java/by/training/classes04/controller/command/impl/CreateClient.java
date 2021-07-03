package by.training.classes04.controller.command.impl;

import by.training.classes04.bean.MessageManager;
import by.training.classes04.controller.command.Command;
import by.training.classes04.service.impl.BankOperationsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation for creating bill, choice command CREATE_CLIENT
 */
public class CreateClient implements Command {

    static final Logger controllerLog = LogManager.getLogger("ControllerLog");

    /**
     * validate all information and transfers it to service function createUser(String data)
     * @param request have name of command and all information required for creating client
     * @return boolean true if client added. false if not
     */
    @Override
    public boolean execute(String request) {
        StringBuilder builder = new StringBuilder("");
        String[] requestArr = request.split(" ");
        BankOperationsImpl operations = new BankOperationsImpl();

        try {
            builder.append(requestArr[1] + ",");
            builder.append(requestArr[2] + ",");
            builder.append(Integer.parseInt(requestArr[3]) + ",");
            for (int i = 0; i < requestArr.length - 4; i++) {
                builder.append(Double.parseDouble(requestArr[i + 4]) + ",");
            }
            if (operations.createUser(String.valueOf(builder))) {
                controllerLog.info("Client created with data"+ request);
                return true;

            } else {
                controllerLog.info("client wasn't added");
                return false;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            controllerLog.error(e.toString());
            return false;
        }
    }
}
