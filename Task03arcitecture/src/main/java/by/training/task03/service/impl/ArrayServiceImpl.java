package by.training.task03.service.impl;

import by.training.task03.bean.Array;

import by.training.task03.bean.Repository;
import by.training.task03.dao.ArrayDAO;
import by.training.task03.dao.exeption.DaoException;
import by.training.task03.dao.factory.DAOFactory;
import by.training.task03.service.ArrayService;
import by.training.task03.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayServiceImpl implements ArrayService {

    static final Logger serviceLogger= LogManager.getLogger("ServiceLog");

    public void arrayIni() throws ServiceException{
        DAOFactory daoFactory=DAOFactory.getInstance();
        ArrayDAO arrayDAO=daoFactory.getArrayDAO();
        Repository repository=Repository.getInstance();
        try {
            repository.setArray(arrayDAO.readFile("ArrayForSort.txt"));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        System.out.println(repository.getArray().toString());
    }

public void bubbleSort(Array array){
        serviceLogger.info("User chose bubble sort");
    for(int i=0;i<array.getLenght()-1;i++){
        for(int j=0;j<array.getLenght()-1-i;j++){
            if(array.getElement(j)>array.getElement(j+1)){
                array.swap(j,j+1);
            }
        }
    }
}

public void shakeSort(Array array){
    serviceLogger.info("User chose shake sort");
    boolean swapped=true;
    int start=0;
    int end=array.getLenght()-1;
    while (swapped){
        swapped=false;
        for(int i=start;i<end;i++){
            if(array.getElement(i)>array.getElement(i+1)){
                array.swap(i,i+1) ;
                swapped=true;
            }
        }
        if(!swapped){
            break;
        }
        swapped=false;
        --end;
        for(int i=end;i>=start;i--){
            if(array.getElement(i)>array.getElement(i+1)){
                array.swap(i,i+1);
                swapped=true;
            }
        }
        if(!swapped){
            break;
        }
        ++start;
    }
}

public void choiceSort(Array array){
    serviceLogger.info("User chose choice sort");
    int min,minj;
    for(int i=0;i< array.getLenght()-1;i++){
        min=i;
        minj=i+1;
        for(int j=i+1;j< array.getLenght()-1;j++){
            if(array.getElement(minj)>array.getElement(j+1)){
                minj=j+1;
            }
        }
        if(array.getElement(min)>array.getElement(minj)){
            array.swap(min,minj);
        }
    }
}

public void insertionSort(Array array){
    serviceLogger.info("User chose insert sort");
    for (int i = 1; i < array.getLenght(); ++i) {
        double key = array.getElement(i);
        int j = i - 1;
        while (j >= 0 && array.getElement(i) > key) {
            array.setElement(j+1,array.getElement(j));
            j = j - 1;
        }
        array.setElement(j + 1,key);
    }
}

public void shellSort(Array array){
    serviceLogger.info("User chose shell sort");
    int length=array.getLenght();
    for (int interval = length / 2; interval > 0; interval /= 2) {
        for (int i = interval; i < length; i += 1) {
            double temp = array.getElement(i);
            int j;
            for (j = i; j >= interval && array.getElement(j - interval) > temp; j -= interval) {
                array.setElement(j, array.getElement(j - interval));
            }
            array.setElement(j,temp);
        }
    }
}

public void mergeSort(double array[],int low,int high){
    serviceLogger.info("User chose merge sort");
        if (high <= low) return;
        int mid = (low+high)/2;
        mergeSort(array, low, mid);
        mergeSort(array, mid+1, high);
        merge(array, low, mid, high);
}

    public static void merge(double[] array, int low, int mid, int high) {
        double leftArray[] = new double[mid - low + 1];
        double rightArray[] = new double[high - mid];
        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];

        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = low; i < high + 1; i++) {

            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {

                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {

                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

}
