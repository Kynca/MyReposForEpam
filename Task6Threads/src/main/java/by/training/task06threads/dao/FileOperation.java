package by.training.task06threads.dao;

import by.training.task06threads.dao.exception.DaoException;

public interface FileOperation {
    String readFile(String filename) throws DaoException;
}
