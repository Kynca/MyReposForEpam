package by.training.finaltask.dao;

import by.training.finaltask.bean.Entity;
import by.training.finaltask.dao.exception.DaoException;

import java.util.List;

public interface Dao<K, T extends Entity> {

    List<T> findAll() throws DaoException;

    T findById(K id) throws DaoException;

    boolean delete(K id) throws DaoException;

    boolean create(T entity) throws DaoException;

    boolean update(T entity) throws DaoException;


}