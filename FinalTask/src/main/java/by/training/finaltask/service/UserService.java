package by.training.finaltask.service;


import by.training.finaltask.bean.entities.User;
import by.training.finaltask.service.excpetion.ServiceException;

import java.util.List;

public interface UserService {
    boolean registration(User user) throws ServiceException;
    User login(String login, String password) throws ServiceException;
    boolean changePass(String login, String password, String newPassword) throws  ServiceException;
    boolean deleteUser(Integer id) throws ServiceException;
    List<User> viewUsers() throws ServiceException;
    User findUser(Integer id) throws ServiceException;
    boolean updateUser(User user) throws ServiceException;
}
