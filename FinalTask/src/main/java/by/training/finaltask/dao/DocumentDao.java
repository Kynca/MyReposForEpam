package by.training.finaltask.dao;

import by.training.finaltask.bean.entities.Document;
import by.training.finaltask.dao.exception.DaoException;

import java.util.List;

public interface DocumentDao extends Dao< Integer,Document> {
    List<Document> findByUserId(Integer id) throws DaoException;
    List<Document> findByDeanId(Integer id) throws DaoException;
    List<Document> getTypes() throws DaoException;
    boolean deleteUserReferences(Integer id) throws DaoException;
}
