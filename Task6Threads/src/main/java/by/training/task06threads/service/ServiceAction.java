package by.training.task06threads.service;

import by.training.task06threads.service.exception.ServiceException;

public interface ServiceAction {
    void matrixInitialisation(String filename, String delimiter) throws ServiceException;
    void threadInitialisation(String filename, String delimiter) throws ServiceException;
    void cyclicBarrierFill();
    void semaphoreFill();
    void lockFill();
    void poolFill();
}
