package by.training.finaltask.service.impl;

import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.DeanDao;
import by.training.finaltask.dao.daoimpl.DeanDaoImpl;
import by.training.finaltask.dao.daoimpl.DocumentDaoImpl;
import by.training.finaltask.dao.daoimpl.StudentDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.BaseService;
import by.training.finaltask.service.DocumentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DocumentServiceImpl extends BaseService implements DocumentService {
    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    @Override
    public Document viewDocument(Integer id) throws ServiceException {
        if (id != null && id > 0) {
            serviceLog.info("id = " + id);
            DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
            transaction.init(documentDao);
            try {
                Document document = documentDao.findById(id);
                return document;
            } catch (DaoException e) {
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } else {
            serviceLog.info("incorrect id");
            return null;
        }
    }

    @Override
    public List<Document> viewDocuments(Integer id, boolean isDean) throws ServiceException {
        serviceLog.debug("is dean request =" + isDean + " id = " + id);
        DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
        transaction.init(documentDao);
        List<Document> documents = new ArrayList<>();
        try {
            if (id != null || id > 0) {
                serviceLog.info("correct id");
                if (isDean) {
                    documents = documentDao.findByDeanId(id);
                } else {
                    documents = documentDao.findByUserId(id);
                }
            }
            return documents;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean addFile(String filepath, Integer id) throws ServiceException {
        serviceLog.info("in add file service id = " + id + " filepath = " + filepath);
        DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
        transaction.init(documentDao);
        try {
            if (id == null || id < 0){
                serviceLog.info("incorrect id");
                return false;
            }
            Document document = documentDao.findById(id);
            if (document != null && !filepath.isEmpty() && document.getDocumentPath() == null) {
                serviceLog.info("founded document is ready to add path");
                document.setDocumentPath(filepath);
                return documentDao.update(document);
            } else {
                serviceLog.info("founded document is already setted");
                transaction.rollback();
                return false;
            }
        } catch (DaoException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean deleteDocument(Integer id) throws ServiceException {
        DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
        transaction.init(documentDao);
        try {
            return documentDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean createDocument(Document document) throws ServiceException {
        serviceLog.info("document to create = " + document);
        if (document != null && document.getDeliveryType() != null && document.getTypeId() != null) {
            if (document.getReceiverMail() != null && !document.getReceiverMail().isEmpty()) {
                Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:.[a-zA-Z0-9_+&*-]" +
                        "+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}");
                boolean result = pattern.matcher(document.getReceiverMail()).matches();
                if (!result) {
                    serviceLog.debug("mail don't match");
                    return false;
                }
            }
            serviceLog.info("document is correct");
            DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
            transaction.init(documentDao);
            try {
                return documentDao.create(document);
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } else {
            serviceLog.info("incorrect document");
            return false;
        }
    }

    @Override
    public List<Document> viewTypes() throws ServiceException {
        DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
        transaction.init(documentDao);
        try {
            return documentDao.getTypes();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }
}
