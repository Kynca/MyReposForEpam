package by.training.finaltask.service;

import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.excpetion.ServiceException;
import by.training.finaltask.service.impl.*;
import by.training.finaltask.service.transaction.Transaction;
import by.training.finaltask.service.transaction.TransactionFactory;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserServiceImpl userService = new UserServiceImpl();
    private final DeanServiceImpl deanService = new DeanServiceImpl();
    private final DocumentServiceImpl documentService = new DocumentServiceImpl();
    private final MarkServiceImpl markService = new MarkServiceImpl();
    private final StudentServiceImpl studentService = new StudentServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public DeanServiceImpl getDeanService() throws ServiceException {
        deanService.setTransaction(getTransaction());
        return deanService;
    }

    public DocumentServiceImpl getDocumentService() throws ServiceException {
        documentService.setTransaction(getTransaction());
        return documentService;
    }

    public MarkServiceImpl getMarkService() throws ServiceException {
        markService.setTransaction(getTransaction());
        return markService;
    }

    public StudentServiceImpl getStudentService() throws ServiceException {
        studentService.setTransaction(getTransaction());
        return studentService;
    }

    public UserServiceImpl getUserService() throws ServiceException {
        userService.setTransaction(getTransaction());
        return userService;
    }

    private Transaction getTransaction() throws ServiceException {
        TransactionFactory factory = null;
        try {
            factory = new TransactionFactory();
        } catch (DaoException e) {
            throw new ServiceException("can not create transaction", e);
        }
        return factory.createTransaction();
    }
}
