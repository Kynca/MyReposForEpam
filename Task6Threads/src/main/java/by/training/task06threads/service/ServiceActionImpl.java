package by.training.task06threads.service;

import by.training.task06threads.bean.MatrixStorage;
import by.training.task06threads.bean.ResourcePool;
import by.training.task06threads.dao.FileOperationImpl;
import by.training.task06threads.dao.exception.DaoException;
import by.training.task06threads.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


public class ServiceActionImpl implements ServiceAction {

    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");
    MatrixStorage storage = MatrixStorage.getInstance();

    public void matrixInitialisation(String filename, String delimiter) throws ServiceException {
        FileOperationImpl impl = new FileOperationImpl();
        try {
            String matrixData = impl.readFile(filename);
            serviceLog.info(matrixData);
            String[] matrixDataArray = matrixData.split(delimiter);
            int matrixSize = Integer.parseInt(matrixDataArray[0]);

            if (matrixDataArray.length - 1 != (matrixSize * matrixSize)) {
                throw new ServiceException("file has incorrect data, matrix size do not equals real matrix size");
            }

            double matrix[][] = new double[matrixSize][matrixSize];
            int k = 0;
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    if (j == i) {
                        matrix[i][j] = 0;
                        continue;
                    }
                    matrix[i][j] = Double.parseDouble(matrixDataArray[++k]);
                }
            }
            serviceLog.info(matrix.toString());
            storage.setArrays(matrix);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } catch (NumberFormatException e) {
            throw new ServiceException("Incorrect data matrix is not filled with numbers or size in not a number");
        }
    }

    public void threadInitialisation(String filename, String delimiter) throws ServiceException {
        FileOperationImpl impl = new FileOperationImpl();
        try {
            String threadData = impl.readFile(filename);
            serviceLog.info(threadData);
            String[] threadDataArray = threadData.split(delimiter);
            int lenght = threadDataArray.length;
            Set set = new HashSet();

            if (Integer.parseInt(threadDataArray[0]) > lenght) {
                throw new ServiceException("amount of threads and threads data are nor equals");
            }

            int[] numbers = new int[lenght - 1];

            for (int i = 1; i < lenght; i++) {
                int value = Integer.parseInt(threadDataArray[i]);
                if (set.contains(value)) {
                    throw new ServiceException("Same id");
                }
                set.add(value);
                numbers[i - 1] = value;
            }
            if(numbers.length+1 < lenght){
                throw new ServiceException("amount of threads and threads data are nor equals");
            }

            serviceLog.info(numbers.toString());
            storage.setThreadsParameters(numbers.length, numbers);
        } catch (DaoException | NumberFormatException e) {
            throw new ServiceException(e);
        }
    }

    public void cyclicBarrierFill() {
        CyclicBarrier barrier = new CyclicBarrier(storage.getNumberOfThreads());
        for (int i = 0; i < storage.getNumberOfThreads(); i++) {
            Thread barrierFiller = new Thread(new BarrierFill(barrier, storage.getThreadNumber(i)));
            barrierFiller.start();
        }
    }

    public void semaphoreFill() {
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < storage.getNumberOfThreads(); i++) {
            Thread semaphoreFiller = new Thread(new SemaphoreFill(semaphore, storage.getThreadNumber(i)));
            semaphoreFiller.start();
        }
    }

    public void lockFill() {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < storage.getNumberOfThreads(); i++) {
            Thread lockFiller = new Thread(new LockFill(lock, storage.getThreadNumber(i)));
            lockFiller.start();
        }
    }

    public void poolFill() {
        ResourcePool pool = new ResourcePool(storage.getSize());
        for (int i = 0; i < storage.getNumberOfThreads(); i++) {
            Thread poolFiller = new Thread(new PoolFill(pool, storage.getThreadNumber(i)));
            poolFiller.start();
        }
    }

}

