package by.training.task03.service;

import by.training.task03.bean.Array;
import by.training.task03.service.exception.ServiceException;

public interface ArrayService {
    void bubbleSort(Array array);
    void shakeSort(Array array);
    void choiceSort(Array array);
    void insertionSort(Array array);
    void shellSort(Array array);
    void mergeSort(Array array,int low, int high);
    void arrayIni() throws ServiceException;
}
