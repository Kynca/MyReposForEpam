package by.training.finaltask.service.impl;

import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.DeanDao;
import by.training.finaltask.dao.StudentDao;
import by.training.finaltask.dao.daoimpl.DeanDaoImpl;
import by.training.finaltask.dao.daoimpl.DocumentDaoImpl;
import by.training.finaltask.dao.daoimpl.StudentDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.BaseService;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentServiceImpl extends BaseService implements StudentService {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Student viewInfo(Integer id) throws ServiceException {
        StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
        transaction.init(studentDao);
        try {
            return studentDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean updateInfo(Student student) throws ServiceException {
        if (student != null) {
            StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
            transaction.init(studentDao);
            try {
                return studentDao.update(student);
            } catch (DaoException e) {
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } else {
            //logger
            return false;
        }
    }

    @Override
    public List<Student> viewStudents() throws ServiceException {
        StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
        transaction.init(studentDao);
        try {
            return studentDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Map<Student, Dean> viewStudentsInfo() throws ServiceException {
        try {
            Map<Student, Dean> studentDeanMap = new HashMap<>();
            StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
            DeanDaoImpl deanDao = DaoFactory.getInstance().getDeanDao();
            transaction.init(studentDao, deanDao);
            List<Student> students = studentDao.findAll();
            for (Student student : students) {
                Dean dean = deanDao.findById(student.getDeanId());
                studentDeanMap.put(student, dean);
            }
            return studentDeanMap;
        } catch (DaoException e) {
            debugLog.debug("catch daoException");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createStudent(Student student) throws ServiceException {
        if (student != null) {
            StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
            transaction.init(studentDao);
            try {
                return studentDao.create(student);
            } catch (DaoException e) {
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        } else {
            //logger
            return false;
        }
    }

    public boolean deleteStudent(Integer id) throws ServiceException {
        if (id != null && id > 0) {
            StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
            DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
            transaction.init(studentDao, documentDao);
            try {
                if (studentDao.findById(id) != null) {
                    List<Document> documents = documentDao.findByNotDocId(id, false);
                    if (documents.size() > 0) {
                        for (Document document : documents) {
                            if (!documentDao.deleteUserReferences(id)) {
                                debugLog.debug("cannot update doc " + document);
                                return false;
                            }
                        }
                    }
                    return studentDao.delete(id);
                } else {
                    return false;
                }
            } catch (DaoException e) {
                debugLog.debug("catch daoException");
                transaction.rollback();
                throw new ServiceException(e);
            } finally {
                transaction.endTransaction();
            }
        }
        return false;
    }
}
