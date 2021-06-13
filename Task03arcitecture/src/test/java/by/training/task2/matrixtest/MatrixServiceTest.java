package by.training.task2.matrixtest;

import by.training.task03.service.MatrixService;
import by.training.task03.service.exception.ServiceException;
import by.training.task03.service.factory.ServiceFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class MatrixServiceTest {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    MatrixService matrixService = serviceFactory.getMatrixService();

    @DataProvider(name = "scenario for sum")
    public Object[][] sumScenarios() {
        return new Object[][][]{
                {new double[][]{{10, 3, 1}, {20, 31, 2}}, new double[][]{{100, 9, 1}, {200, 3, -1}}, new double[][]{{110, 12, 2}, {220, 34, 1}}},
                {new double[][]{{1, 2}, {3, 5}, {7, 1}, {-100, -1321}}, new double[][]{{0, 4}, {1, 2}, {3, -1}, {100, 2}},
                        new double[][]{{1, 6}, {4, 7}, {10, 0}, {0, -1319}}},
                {new double[][]{{-4, -5}, {0, 0}}, new double[][]{{25, 36}, {3, 1}}, new double[][]{{21, 31}, {3, 1}}},
        };
    }

    @Test(description = "scenario with sum", dataProvider = "scenario for sum" )
    public void sumScenario(double[][] m1, double[][] m2, double[][] res) throws ServiceException {
        assertEquals(matrixService.sumMatrix(m1, m2), res);
    }

    @DataProvider(name = "scenario for subtraction")
    public Object[][] subScenarios() {
        return new Object[][][]{
                {new double[][]{{10, 3}, {20, 31}}, new double[][]{{100, 9}, {200, 3}}, new double[][]{{-90, -6}, {-180, 28}}},
                {new double[][]{{1, 2}, {3, 5}, {7, 1}, {-100, -1321}}, new double[][]{{0, 4}, {1, 2}, {3, -1}, {100, 2}},
                        new double[][]{{1, -2}, {2, 3}, {4, 2}, {-200, -1323}}},
                {new double[][]{{-4, -5}, {0, 0}}, new double[][]{{25, 36}, {3, 1}}, new double[][]{{-29, -41}, {-3, -1}}},
        };
    }

    @Test(description = "scenario for subtraction", dataProvider = "scenario for subtraction")
    public void subScenario(double[][] m1, double[][] m2, double[][] res) throws ServiceException {
        assertEquals(matrixService.matrixSubtraction(m1, m2), res);
    }

    @DataProvider(name = "transposition")
    public Object[][] transScenarios() {
        return new Object[][]{
                {new double[][]{{2, 1}, {30, -100}}, new double[][]{{2, 30}, {1, -100}}},
                {new double[][]{{-5, 30}, {10, 2}, {60, 2}}, new double[][]{{-5, 10, 60}, {30, 2, 2}}},
                {new double[][]{{1, 6, 2}, {-1, 5, 3}}, new double[][]{{1, -1}, {6, 5}, {2, 3}}},

        };
    }

    @Test(description = "scenario with transposition", dataProvider = "transposition")
    public void transTest(double[][] nums, double[][] result) {
        assertEquals(matrixService.transposition(nums), result);
    }

    @DataProvider(name = "Multiplication on number")
    public Object[][] multNumScenarios() {
        return new Object[][]{
                {new double[][]{{2, 1}, {30, -100}}, 2, new double[][]{{4, 2}, {60, -200}}},
                {new double[][]{{-5, 30}, {10, 2}, {60, 2}}, -2, new double[][]{{10, -60}, {-20, -4}, {-120, -4}}},
                {new double[][]{{1, 6, 2}, {-1, 5, 3}}, 1, new double[][]{{1, 6, 2}, {-1, 5, 3}}}

        };
    }

    @Test(description = "matrix multiplied on number", dataProvider = "Multiplication on number")
    public void multOnNumTest(double[][] nums, int num, double[][] result) {
        assertEquals(matrixService.multiplicationOnNum(nums, num), result);
    }

    @DataProvider(name = "scenario for multiplication")
    public Object[][] multScenarios() {
        return new Object[][][]{
                {new double[][]{{10, 3}, {20, 31}}, new double[][]{{100, 9}, {200, 3}}, new double[][]{{1600, 99}, {8200, 273}}},
                {new double[][]{{1, 2}, {3, 5}, {7, 1}, {-100, -1321}}, new double[][]{{-6, 4, 1, 2}, {23, -1, 100, 273}},
                        new double[][]{{40, 2, 201, 548}, {97, 7, 503, 1371}, {-19, 27, 107, 287}, {-29783, 921, -132200, -360833}}},
        };
    }

    @Test(description = "scenario for multiplication", dataProvider = "scenario for multiplication")
    public void multScenarioTest(double[][] m1, double[][] m2, double[][] res) throws ServiceException {
        System.out.println(Arrays.deepToString(m1)+ " "+Arrays.deepToString(m2)+" "+m1.length+","+m2[0].length+Arrays.deepToString( res));
        assertEquals(matrixService.multiplication(m1, m2), res);
    }


}
