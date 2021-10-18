package by.training.finaltask.service.impl;

import by.training.finaltask.bean.entities.*;
import by.training.finaltask.dao.DaoFactory;
import by.training.finaltask.dao.daoimpl.DeanDaoImpl;
import by.training.finaltask.dao.daoimpl.DocumentDaoImpl;
import by.training.finaltask.dao.daoimpl.StudentDaoImpl;
import by.training.finaltask.dao.daoimpl.UserDaoImpl;
import by.training.finaltask.dao.exception.DaoException;
import by.training.finaltask.service.BaseService;
import by.training.finaltask.service.StudentService;
import by.training.finaltask.service.excpetion.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Pattern;

public class StudentServiceImpl extends BaseService implements StudentService {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");

    @Override
    public Student viewInfo(Integer id, boolean isAdmin) throws ServiceException {
        if(id == null){
            return null;
        }
        StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
        UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
        transaction.init(studentDao, userDao);
        debugLog.debug("in find");
        try {
            Student student = studentDao.findById(id);
            User user = userDao.findById(id);
            if(user.getRole() == Role.STUDENT) {
                return student;
            }
            if(isAdmin && user.getRole() != Role.ADMINISTRATOR){
                return student;
            }
            return null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public boolean updateInfo(Student student) throws ServiceException {
        if (student != null) {
            if(!studentCheck(student)){
                debugLog.debug("student dont match");
                return false;
            }
            StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
            UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
            transaction.init(studentDao, userDao);
            try {
                if(student.getId() != null){
                    User user = userDao.findById(student.getId());
                    if(user.getRole() == Role.ADMINISTRATOR){
                        debugLog.debug("student is admin");
                        return false;
                    }
                    return studentDao.update(student);
                }
                debugLog.debug("id = null");
               return false;
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
    public List<Student> viewDeanStudents(Integer id) throws ServiceException {
        StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
        transaction.init(studentDao);
        if(id == null){
            return null;
        }
        try {
            return studentDao.findDeanStudent(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Map<Student, Dean> viewStudentsInfo() throws ServiceException {
        debugLog.debug("in service");
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
            UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
            transaction.init(studentDao, userDao);
            try {
                if (studentCheck(student)) {
                    Integer id = userDao.createGeneratedUser(student.getName(), student.getLastname());
                    if (id == null) {
                        transaction.rollback();
                        return false;
                    }
                    if (studentDao.create(student)) {
                        return true;
                    } else {
                        transaction.rollback();
                        return false;
                    }
                }
                return false;
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

    public boolean deleteStudent(Integer id) throws ServiceException {
        if (id != null && id > 0) {
            StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
            DocumentDaoImpl documentDao = DaoFactory.getInstance().getDocumentDao();
            UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
            transaction.init(studentDao, documentDao, userDao);
            try {
                User user = userDao.findById(id);
                if(user == null || user.getRole() == Role.ADMINISTRATOR){
                    return false;
                }

                if (studentDao.findById(id) != null) {
                    List<Document> documents = documentDao.findByUserId(id);
                    if (documents.size() > 0) {
                            if (!documentDao.deleteUserReferences(id)) {
                                return false;
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

    private boolean studentCheck(Student student) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:.[a-zA-Z0-9_+&*-]" +
                "+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}");
        boolean result = pattern.matcher(student.getMail()).matches();
        if (!result) {
            debugLog.debug("mail dont macth" + student.getMail());
            return false;
        }
        if (student.getName() == null || student.getDeanId() == null || student.getDate() == null ||
                student.getLastname() == null || student.getPatronymic() == null) {
            debugLog.debug("something dont match");
            return false;
        }

        pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        if (!pattern.matcher(student.getDate()).matches()) {
            debugLog.debug("data dont macth");
            return false;
        }
        return true;
    }
}
