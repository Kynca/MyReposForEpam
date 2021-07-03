package by.training.classes04.dao;

/**
 * interface with all methods used in layer Dao
 */
public interface FileOperations {
    String readFile(String filename);

    void writeFile(String filename, String data);
}
