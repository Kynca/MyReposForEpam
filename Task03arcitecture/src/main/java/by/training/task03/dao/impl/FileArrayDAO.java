package by.training.task03.dao.impl;

import by.training.task03.bean.Array;
import by.training.task03.dao.ArrayDAO;
import by.training.task03.dao.exeption.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileArrayDAO implements ArrayDAO {

    static final Logger daoLog = LogManager.getLogger(FileArrayDAO.class.getName());

    public Array readFile(String fileName) throws DaoException {
        File file = new File(fileName);
        int index = 0;
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextInt()) {
                index++;
                fileReader.nextInt();
            }
            Array array = new Array(index);
            fileReader = new Scanner(file);
            for (int i = 0; i < index; i++) {
                array.setElement(i, fileReader.nextInt());
            }
            fileReader.close();
            daoLog.info("Array read successful");
            return array;
        } catch (FileNotFoundException e) {
            throw new DaoException("File not found",e);
        }
    }
}
