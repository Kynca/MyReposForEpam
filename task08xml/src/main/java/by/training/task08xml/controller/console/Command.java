package by.training.task08xml.controller.console;

import by.training.task08xml.bean.Tariff;

import java.util.List;

public interface Command {
    List<Tariff> execute(String request);
}
