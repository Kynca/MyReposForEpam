package by.training.finaltask.dao;

import by.training.finaltask.bean.entities.Mark;
import by.training.finaltask.dao.exception.DaoException;

import java.util.List;

public interface MarkDao extends Dao< Integer, Mark> {
    List<Mark> findByStudentId(Integer id) throws DaoException;
    Integer findSubject(String name) throws DaoException;
}
