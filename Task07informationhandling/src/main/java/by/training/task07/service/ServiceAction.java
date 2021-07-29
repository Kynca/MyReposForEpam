package by.training.task07.service;

import by.training.task07.bean.composite.Component;
import by.training.task07.service.exceptrion.ServiceException;

import java.util.List;

public interface ServiceAction {
    void parseText(String text) throws ServiceException;
    String collectText();
    List<Component> sortParagraph();
    List<Component> sortWords();
    List<Component> sortLexeme(String symbol);
}
