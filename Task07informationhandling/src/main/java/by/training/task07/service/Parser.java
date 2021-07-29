package by.training.task07.service;

import by.training.task07.bean.composite.Component;

/**
 * method which all parsers will implements
 */
public interface Parser {
    void parse(String part, Component root);
}
