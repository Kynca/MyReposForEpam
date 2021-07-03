package by.training.classes04.dao.impl;

import by.training.classes04.dao.FileOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * implements interface FileOperations methods
 */
public class DaoImpl implements FileOperations {

    static final Logger daoLogger = LogManager.getLogger(DaoImpl.class.getName());

    /**
     * function which write data in file
     * @param filename name of file
     * @param data     information for writing in file
     */
    @Override
    public void writeFile(String filename, String data) {
        File file = new File(filename);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(data);
            daoLogger.info(data + "is printed on file");
            fileWriter.append('\n');
            fileWriter.close();
        } catch (IOException e) {
            daoLogger.error("writeFile is broken");
        }
    }

    /**
     * read file and saves information in StringBuilder
     * @param filename name of file
     * @return String data from file, null if file isEmpty
     */
    @Override
    public String readFile(String filename) {
        File file = new File(filename);
        daoLogger.info("information read from file " + filename);
        StringBuilder builder = new StringBuilder();
        if (file.length() == 0) {
            return null;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine() + ",");
            }
            return String.valueOf(builder);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
