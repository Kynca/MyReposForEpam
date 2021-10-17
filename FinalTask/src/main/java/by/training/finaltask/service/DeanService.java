package by.training.finaltask.service;

import by.training.finaltask.bean.entities.Dean;
import by.training.finaltask.bean.entities.University;
import by.training.finaltask.service.excpetion.ServiceException;

import java.util.List;
import java.util.Map;

public interface DeanService {
    boolean deleteDean(Integer id) throws ServiceException;
    boolean updateDean(Dean dean) throws ServiceException;
    boolean create(Dean dean) throws ServiceException;
    List<Dean> viewDeans() throws ServiceException;
    Dean findDean(Integer id) throws ServiceException;
    Map<Dean, List<University>> findDeanUniversities(Integer id) throws ServiceException;
    List<University> findUniversities() throws ServiceException;
}
