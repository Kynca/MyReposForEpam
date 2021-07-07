package by.training.task05.dao;

import by.training.task05.dao.exception.DaoException;

import java.io.FileNotFoundException;

public interface FileActions {
    String readFile(String filename) throws DaoException, FileNotFoundException;
    void writeFile(String filename,String data) throws DaoException;
}
