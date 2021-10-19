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

    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    @Override
    public Student viewInfo(Integer id, boolean isAdmin) throws ServiceException {
        if(id == null){
            return null;
        }
        StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
        UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
        transaction.init(studentDao, userDao);
        serviceLog.debug("in find");
        try {
            Student student = studentDao.findById(id);
            User user = userDao.findById(id);
            if(user == null){
                return null;
            }
            if(user.getRole() == Role.STUDENT || user.getRole() == Role.DEAN) {
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
                serviceLog.info("student dont match");
                return false;
            }
            StudentDaoImpl studentDao = DaoFactory.getInstance().getStudentDao();
            UserDaoImpl userDao = DaoFactory.getInstance().getUserDao();
            transaction.init(studentDao, userDao);
            try {
                if(student.getId() != null){
                    User user = userDao.findById(student.getId());
                    if(user.getRole() == Role.ADMINISTRATOR){
                        serviceLog.info("student is admin");
                        return false;
                    }
                    return studentDao.update(student);
                }
                serviceLog.info("id = null");
               return false;
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
    public List<Student> viewDeanStudents(Integer id) throws ServiceException {
        serviceLog.info("in view dean student");
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
        serviceLog.debug("in service");
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
            serviceLog.info("mail dont macth" + student.getMail());
            return false;
        }
        if (student.getName() == null || student.getDeanId() == null || student.getDate() == null ||
                student.getLastname() == null || student.getPatronymic() == null) {
            serviceLog.info("something dont match");
            return false;
        }
        if(student.getName().length() > 15 || student.getPatronymic().length() > 20 || student.getLastname().length() > 25){
            return false;
        }

        pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        if (!pattern.matcher(student.getDate()).matches()) {
            serviceLog.info("data dont macth");
            return false;
        }
        return true;
    }
}
