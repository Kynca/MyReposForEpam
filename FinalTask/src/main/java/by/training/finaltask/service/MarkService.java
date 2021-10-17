package by.training.finaltask.service;

import by.training.finaltask.bean.entities.Mark;
import by.training.finaltask.service.excpetion.ServiceException;

import java.util.List;

public interface MarkService {
    List<Mark> viewMarks(Integer id) throws ServiceException;
    boolean setRate(Mark mark) throws ServiceException;
    boolean createMark(Mark mark) throws ServiceException;
}
