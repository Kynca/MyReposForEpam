package by.training.task05.dao;

import by.training.task05.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Scanner;

public class DaoImpl implements FileActions {

    static final Logger daoLog = LogManager.getLogger(DaoImpl.class);

    @Override
    public String readFile(String filename) throws DaoException {
        StringBuilder builder = new StringBuilder("");
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
            return String.valueOf(builder);
        } catch (FileNotFoundException e) {
            throw new DaoException("file not found", e);
        }

    }

    @Override
    public void writeFile(String filename, String data) throws DaoException{
        File file = new File(filename);
        try {
            FileWriter writer = new FileWriter(file,true);
            writer.write(data+",");
            daoLog.info("saved data: "+ data);
            writer.close();
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
