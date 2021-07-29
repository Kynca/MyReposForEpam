package by.training.task07.controller;


import java.util.List;

public interface Command {
    List execute(String request);
}