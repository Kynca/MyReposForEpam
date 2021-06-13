package by.training.task03.controller.command.impl;

import by.training.task03.controller.command.Command;

public class WrongRequest implements Command {
    public String execute(String request) {
        return "There is no such options, please try again";
    }
}
