package by.training.finaltask.service.impl;

import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.daoimpl.DocumentDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.BaseService;
import by.training.finaltask.service.DocumentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentServiceImpl extends BaseService implements DocumentService {
    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Document viewDocument(Integer id) throws ServiceException {
        if (id != null && id > 0) {
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
            //logger
            return null;
        }
    }

    @Override
    public List<Document> viewDocuments(Integer id, boolean isDean) throws ServiceException {
        debugLog.debug("in service");
        DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
        transaction.init(documentDao);
        debugLog.debug("init Transaction");
        List<Document> documents = null;
        try {
            if (id == null) {
                documents = documentDao.findAll();
            } else if (id > 0) {
                documents = documentDao.findByNotDocId(id, isDean);
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
        DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
        transaction.init(documentDao);
        try {
            Document document = documentDao.findById(id);
            if (document != null && !filepath.isEmpty()) {
                document.setDocumentPath(filepath);
                return documentDao.update(document);
            } else {
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
        debugLog.debug("in service");
        if (document != null && document.getDeliveryType() != null && document.getTypeId() != null) {
            if (document.getReceiverMail() != null) {
                Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:.[a-zA-Z0-9_+&*-]" +
                        "+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}");
                debugLog.debug(document.getReceiverMail());
                boolean result = pattern.matcher(document.getReceiverMail()).matches();
                debugLog.debug(result);
                if(!result){
                    debugLog.debug("mail don't match");
                    return false;
                }
            }
            DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
            transaction.init(documentDao);
            debugLog.debug("init transaction");
            try {
                return documentDao.create(document);
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } else {
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
