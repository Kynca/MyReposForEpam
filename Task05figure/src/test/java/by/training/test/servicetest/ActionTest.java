package by.training.test.servicetest;

import by.training.task05.bean.Cube;
import by.training.task05.service.cubeimpl.CubeRegistrarCalcImpl;
import by.training.task05.service.cubeimpl.CubeValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class ActionTest {
    CubeValidator validator;

    @DataProvider(name = "validator test")
    public Object[][] validatorData(){
        return new Object[][]{
                {new double[][]{{0,0,0},{4,0,0},{4,4,0},{0,4,0},{0,0,4},{4,0,4},{4,4,4},{0,4,4}},true},
                {new double[][]{{0,0,1},{0,4,0},{4,4,0},{4,0,0},{0,0,4},{0,4,4},{4,4,4},{4,0,4}},false},
                {new double[][]{{0,0,0},{0,4,0},{4,4,0},{4,3,0},{0,0,4},{0,4,4},{4,4,4},{4,0,4}},false},
                {new double[][]{{0,0,0},{4.05,0,0},{4.05,3.54,1.98},{0,3.54,1.98},{0,-1.98,3.54},{4.05,-1.98,3.54},{4.05,1.55,5.52},{0,-1.55,5.52}},true},
        };
    }

    @Test(description = "test with different data for CubeValidator", dataProvider = "validator test")
    public void validatorTest(double[][] points,boolean result){
        Cube cube = new Cube((points));
        validator = new CubeValidator(cube.getPoints());
        assertEquals(validator.isCube(),result);
    }

   CubeRegistrarCalcImpl registrarCalc = new CubeRegistrarCalcImpl();

    @DataProvider(name = "scenario for size calc")
    public Object[][] sizeData(){
        Cube cube = new Cube(new double[][]{{0,0,0},{4,0,0},{12,4,2},{0,0,3.26},{-2,0,4},{3,0,4},{15,-3.21,100},{0,4,4}});
        return new Object[][]{
                {cube.getPoint(0),cube.getPoint(1),4},
                {cube.getPoint(0),cube.getPoint(3),3.26},
                {cube.getPoint(1),cube.getPoint(2),16.62},
                {cube.getPoint(3),cube.getPoint(7),8.29},
                {cube.getPoint(2),cube.getPoint(4),12.32},
                {cube.getPoint(1),cube.getPoint(6),101.84},
                {cube.getPoint(5),cube.getPoint(6),105.6},
        };
    }

    @Test(description = "test for calculating distance between 2 points", dataProvider = "scenario for size calc")
    public void sizeCalcTest(Cube.Point point1, Cube.Point point2, double result){
        assertEquals(registrarCalc.sizeCalc(point1,point2),result,0.01);
    }

    @DataProvider(name = "scenario for volume calc")
    public Object[][] volumeData(){
        return new Object[][]{
                {2,8},
                {1.32,2.29},
                {0.1,0.001},
                {1000, 1_000_000_000},
                {7.36, 398.69},
                {1,1}
        };
    }

    @Test(description = "test for cube volume calculating", dataProvider = "scenario for volume calc")
    public void volumeCalcTest(double size, double result){
        assertEquals(registrarCalc.volumeCalc(size),result,0.01);
    }

    @DataProvider(name = "scenario for area calc")
    public Object[][] areaData(){
        return new Object[][]{
                {2,24},
                {1.32,10.45},
                {0.1,0.06},
                {1000, 6_000_000},
                {7.36, 325.02},
                {1,6}
        };
    }

    @Test(description = "test for cube area calculation", dataProvider = "scenario for area calc")
    public void areaCalcTest(double size, double result){
        assertEquals(registrarCalc.areaCalc(size),result, 0.01);
    }

    @DataProvider(name = "scenario is cube on plane")
    public Object[][] planeData(){
        Cube cube = new Cube(new double[][]{{0,0,0},{4,0,0},{1,1,0},{0,0,3.26},{-2,0,4},{3,0,4},{15,-3.21,100},{0,4,4}});
        return new Object[][]{
                {cube.getPoints(),true}
        };
    }

    @Test(description = "test is 1 of the side of cube is on plane", dataProvider = "scenario is cube on plane")
    public void isOnPlaneTest(Cube.Point[] points, boolean result){
        assertEquals(registrarCalc.isOnPlane(points),result);
    }

}
