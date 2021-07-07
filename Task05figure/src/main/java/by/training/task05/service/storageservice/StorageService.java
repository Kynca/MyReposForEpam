package by.training.task05.service.storageservice;

import by.training.task05.service.exception.ServiceException;


public interface StorageService {
    boolean saveStorage(String filename) throws ServiceException;
    void fillStorage(String filename) throws ServiceException;
}
