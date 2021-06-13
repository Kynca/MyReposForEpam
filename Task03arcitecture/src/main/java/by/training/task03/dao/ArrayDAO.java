package by.training.task03.dao;

import by.training.task03.bean.Array;
import by.training.task03.dao.exeption.DaoException;

public interface ArrayDAO {
    Array readFile(String fileName) throws DaoException;
}
