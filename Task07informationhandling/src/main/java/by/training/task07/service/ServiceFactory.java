package by.training.task07.service;

import by.training.task07.service.actionimpl.ServiceActionImpl;

/**
 * Service factory which gives classes with impl from service layer
 */
public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory(){}

    private ServiceAction action = new ServiceActionImpl();

    public static ServiceFactory getInstance(){
        return instance;
    }

    public ServiceAction getAction() {
        return action;
    }
}
