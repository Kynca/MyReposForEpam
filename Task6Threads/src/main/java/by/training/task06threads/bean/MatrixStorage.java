package by.training.task06threads.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixStorage {

    private static final Logger debugLog = LogManager.getLogger("DebugLog");
    private int changes = 0;
    private static MatrixStorage instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private double[][] originArray;
    private double[][] changedArray;
    private int numberOfThreads;
    int[] threadNumbers;

    private MatrixStorage() {}

    public static MatrixStorage getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new MatrixStorage();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public void setArrays(double[][] matrix) {
        int size = matrix.length;
        originArray = null;
        changedArray = null;
        originArray = new double[size][size];
        changedArray = new double[size][size];
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                originArray[i][j] = matrix[i][j];
                changedArray[i][j] = matrix[i][j];
            }
        }
    }

    public void setThreadsParameters(int numberOfThreads, int[] threadNumbers){
        this.numberOfThreads = numberOfThreads;
        this.threadNumbers = new int[threadNumbers.length];
        this.threadNumbers = Arrays.copyOf(threadNumbers,threadNumbers.length);
    }

    public double getElement(int index){
        return changedArray[index][index];
    }

    public void setElement(int index, int value){
        changedArray[index][index] = value;
        lock.lock();
        changes++;
        debugLog.debug(changes);
        lock.unlock();
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public int getSize(){
        return originArray.length;
    }

    public int getThreadNumber(int index){
        return threadNumbers[index];
    }

    public double[][] getChangedArray() {
        return Arrays.copyOf(changedArray,changedArray.length);
    }

    public int getChanges() {
        try{
            lock.lock();
            return changes;
        }finally {
            lock.unlock();
        }

    }

    public void reset(){
        changedArray = Arrays.copyOf(originArray, originArray.length);
        changes = 0;
        debugLog.debug(changes);
    }
}
