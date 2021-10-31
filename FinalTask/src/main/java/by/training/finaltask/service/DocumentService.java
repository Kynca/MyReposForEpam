package by.training.finaltask.service;

import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.service.excpetion.ServiceException;

import java.util.List;

public interface DocumentService {
    List<Document> viewDocuments(Integer id, boolean isDean) throws ServiceException;
    boolean addFile(String filepath, Integer id) throws ServiceException;
    boolean createDocument(Document document) throws ServiceException;
    List<Document> viewTypes() throws ServiceException;
}
