package by.training.classes04.controller.command.impl;

import by.training.classes04.bean.Bank;
import by.training.classes04.bean.Client;
import by.training.classes04.controller.command.Command;

/**
 * Implementation for output information about clients, choice command SHOW
 */
public class ShowList implements Command {
    /**
     * print clients information on console
     * @param request have name of command
     * @return boolean true constantly
     */
    @Override
    public boolean execute(String request) {
        Bank bank = Bank.getInstance();

        for (Client client : bank.getListOfClient()) {
            System.out.println(client.toString());
        }

        return true;
    }
}
