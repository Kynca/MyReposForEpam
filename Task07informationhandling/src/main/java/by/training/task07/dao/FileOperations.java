package by.training.task07.dao;

import by.training.task07.dao.exception.DaoException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * class with operation for reading file
 */
public class FileOperations {
    /**
     * read file by bytes and transform it to string
     * @param filename name of reading file
     * @return info from file in string
     * @throws DaoException special exception witch occurs when file not found or specified IOException
     */
    public String readFile(String filename) throws DaoException {
        StringBuilder builder = new StringBuilder();
        try {

            FileInputStream reader = new FileInputStream(filename);
            while (reader.available() > 0) {
                builder.append((char) reader.read());
            }

        } catch (FileNotFoundException e) {
            throw new DaoException("file not found");
        } catch (IOException e) {
            throw new DaoException();
        }

        return String.valueOf(builder);
    }
}
