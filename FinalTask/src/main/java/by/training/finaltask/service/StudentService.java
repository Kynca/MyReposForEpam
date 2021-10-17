package by.training.finaltask.service;

import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.Student;
import by.training.finaltask.bean.entities.User;
import by.training.finaltask.service.excpetion.ServiceException;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student viewInfo(Integer id) throws ServiceException;
    boolean updateInfo(Student student) throws ServiceException;
    List<Student> viewDeanStudents(Integer id) throws ServiceException;
    Map<Student, Dean> viewStudentsInfo() throws ServiceException;
    boolean createStudent(Student student) throws ServiceException;
    boolean deleteStudent(Integer id) throws ServiceException;
}
