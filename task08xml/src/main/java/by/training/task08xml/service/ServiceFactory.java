package by.training.task08xml.service;

import by.training.task08xml.service.parserimpl.DomParser;
import by.training.task08xml.service.parserimpl.SaxParser;
import by.training.task08xml.service.parserimpl.StaxParser;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private SaxParser saxParser = new SaxParser();
    private DomParser domParser = new DomParser();
    private StaxParser staxParser = new StaxParser();

    private ServiceFactory(){}

    public SaxParser getSaxParser() {
        return saxParser;
    }

    public DomParser getDomParser() {
        return domParser;
    }

    public StaxParser getStaxParser() {
        return staxParser;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
