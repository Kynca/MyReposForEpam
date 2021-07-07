package by.training.task05.controller.command;

import java.util.List;

public interface Command {
    List execute(String request);
}
