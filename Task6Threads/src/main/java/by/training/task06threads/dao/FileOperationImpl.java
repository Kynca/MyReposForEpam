package by.training.task06threads.dao;

import by.training.task06threads.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FileOperationImpl implements FileOperation {

    private static final Logger daoLog = LogManager.getLogger(FileOperationImpl.class.getName());

    public String readFile(String filename) throws DaoException {
        String matrixData;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fileReader);
            matrixData = reader.readLine();

            daoLog.info(matrixData);

            if (matrixData.trim().length() < 5) {
                throw new DaoException(" File have incorrect data");
            }
            return matrixData;
        } catch (FileNotFoundException e) {
            throw new DaoException("file with name " + filename + " not found");
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
