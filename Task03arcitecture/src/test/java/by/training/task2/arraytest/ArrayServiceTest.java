package by.training.task2.arraytest;

import by.training.task03.bean.Array;
import by.training.task03.dao.ArrayDAO;
import by.training.task03.dao.exeption.DaoException;
import by.training.task03.dao.factory.DAOFactory;
import by.training.task03.service.ArrayService;
import by.training.task03.service.factory.ServiceFactory;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class ArrayServiceTest {
   ServiceFactory serviceFactory=ServiceFactory.getInstance();
   ArrayService arrayService=serviceFactory.getArrayService();
   DAOFactory daoFactory=DAOFactory.getInstance();
   ArrayDAO arrayDAO=daoFactory.getArrayDAO();
   Array array1;
   Array array2;
    {
        try {
            array1 = arrayDAO.readFile("ArrayForSort.txt");
            array2=arrayDAO.readFile("SortedArray.txt");
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }



    @Test(description ="sort testing")
    public void bubbleSortTest(){
       arrayService.bubbleSort(array1);
       assertEquals(array1,array2);
    }

    @Test(description = "shaker sort")
    public void shakerSortTest(){
        arrayService.shakeSort(array1);
        assertEquals(array1,array2);
    }

    @Test(description = "choice sort")
    public void  choiceSortTest(){
        arrayService.choiceSort(array1);
        assertEquals(array1,array2);
    }

    @Test(description = "insertion sort")
    public void insertionSortTest(){
        arrayService.insertionSort(array1);
        assertEquals(array1,array2);
    }

    @Test(description = "shell sort")
    public void shellSortTest(){
        arrayService.shellSort(array1);
        assertEquals(array1,array2);
    }
}
