package by.training.test.servicetest;

import by.training.task05.bean.Cube;
import by.training.task05.bean.CubeRegistrar;
import by.training.task05.bean.CubeStorage;
import by.training.task05.controller.command.impl.Update;
import by.training.task05.service.Repository;
import by.training.task05.service.add.AddCube;
import by.training.task05.service.exception.ServiceException;
import by.training.task05.service.find.*;
import by.training.task05.service.remove.RemoveById;
import by.training.task05.service.sort.CubeComparator;
import by.training.task05.service.update.UpdateCube;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.util.*;

public class SpecificationTest {

    CubeStorage cubeStorage = CubeStorage.getInstance();
    Repository repository = Repository.getInstance();

    Map<Cube, CubeRegistrar> testMap = cubeStorage.getCubeStorage();

    public void fillInfo() {
        testMap.clear();
        Cube cube = new Cube(new double[][]{{0, 0, 0}, {0, 4, 0}, {4, 4, 0}, {4, 0, 0}, {0, 0, 4}, {0, 4, 4}, {4, 4, 4}, {4, 0, 4}});
        Cube cube1 = new Cube(new double[][]{{0, 0, 0}, {4.05, 0, 0}, {4.05, 3.54, 1.98}, {0, 3.54, 1.98}, {0, -1.98, 3.54}, {4.05, -1.98, 3.54}, {4.05, 1.55, 5.52}, {0, -1.55, 5.52}});
        Cube cube2 = new Cube(new double[][]{{0, 0, 4}, {4, 0, 0}, {6.33, 4.6, 2.33}, {2.33, 4.6, 6.33}, {3.25, -3, 3, 7, 25}, {7.25, -3.3, 3.25}, {9.58, 1.3, 5.88}, {5.58, 1.3, 9.58}});
        CubeRegistrar cubeRegistrar = new CubeRegistrar(cube);
        CubeRegistrar cubeRegistrar1 = new CubeRegistrar(cube1);
        CubeRegistrar cubeRegistrar2 = new CubeRegistrar(cube2);
        cube.setId(1);
        cube1.setId(3);
        cube2.setId(2);
        cube.setName("cube1");
        cube1.setName("cube2");
        cube2.setName("cube3");
        testMap.put(cube, cubeRegistrar);
        testMap.put(cube1, cubeRegistrar1);
        testMap.put(cube2, cubeRegistrar2);
    }

    @DataProvider(name = "scenario for add specification")
    public Object[][] addData() {
        return new Object[][]{
                {new double[][]{{0, 0, 0}, {0, 4, 0}, {4, 4, 0}, {4, 0, 0}, {0, 0, 4}, {0, 4, 4}, {4, 4, 4}, {4, 0, 4}}, 1},
                {new double[][]{{0, 0, 1}, {0, 4, 0}, {4, 4, 0}, {4, 0, 0}, {0, 0, 4}, {0, 4, 4}, {4, 4, 4}, {4, 0, 4}}, 0},
                {new double[][]{{0, 0, 0}, {0, 4, 0}, {4, 4, 0}, {4, 3, 0}, {0, 0, 4}, {0, 4, 4}, {4, 4, 4}, {4, 0, 4}}, 0},
                {new double[][]{{0, 0, 0}, {4.05, 0, 0}, {4.05, 3.54, 1.98}, {0, 3.54, 1.98}, {0, -1.98, 3.54}, {4.05, -1.98, 3.54}, {4.05, 1.55, 5.52}, {0, -1.55, 5.52}}, 1},
                {new double[][]{{0, 0, 4}, {4, 0, 0}, {6.33, 4.6, 2.33}, {2.33, 4.6, 6.33}, {3.25, -3.3, 7.25}, {7.25, -3.3, 3.25}, {9.58, 1.3, 5.58}, {5.58, 1.3, 9.58}}, 1},
        };
    }

    @Test(description = "test Add specification", dataProvider = "scenario for add specification")
    public void addTest(double[][] cubePoints, int result) {
        AddCube addCube = new AddCube(cubePoints);
        assertEquals(repository.query(addCube).size(), result);
    }

    @DataProvider(name = "scenario for finding by id")
    public Object[][] findDataByID() {
        fillInfo();
        return new Object[][]{
                {1, 1},
                {3, 1},
                {1982, 0},
                {401, 0},
                {-1, 0}
        };
    }

    @Test(description = "test find specification by id", dataProvider = "scenario for finding by id")
    public void findCubeById(long id, int result) {
        FindById findById = new FindById(id);
        assertEquals(repository.query(findById).size(), result);
    }

    @DataProvider(name = "scenario for finding by name")
    public Object[][] findDataByName() {
        fillInfo();
        return new Object[][]{
                {"cube1", 1},
                {"cube2", 1},
                {"cube3", 1},
                {"cube", 0},
                {"fasdadsadadas", 0}
        };
    }

    @Test(description = "testing finding cube by name", dataProvider = "scenario for finding by name")
    public void findCubeByName(String name, int result) {
        FindByName findByName = new FindByName(name);
        assertEquals(repository.query(findByName).size(), result);
    }

    @Test(description = "testing finding cube on plane")
    public void findCubeOnPlane() {
        fillInfo();
        FindByOnPlane findByOnPlane = new FindByOnPlane();
        assertEquals(repository.query(findByOnPlane).size(), 2);
    }

    @DataProvider(name = "scenario for finding by size")
    public Object[][] findDataBySize() {
        fillInfo();
        return new Object[][]{
                {4, 1},
                {5.65, 1},
                {1982, 0},
                {0, 0},
                {-1, 0}
        };
    }

    @Test(description = "test for finding cube by size", dataProvider = "scenario for finding by size")
    public void FindBySize(double size, int result) {
        FindBySize findBySize = new FindBySize(size);
        assertEquals(repository.query(findBySize).size(), result);
    }

    @DataProvider(name = "scenario for finding cube by size range")
    public Object[][] findDataBySizeRange() {
        fillInfo();
        return new Object[][]{
                {new double[]{1, 6}, 3},
                {new double[]{0, 1}, 0},
                {new double[]{4, 5}, 2},
                {new double[]{3, 4}, 1},
                {new double[]{4.01, 5.7}, 2}
        };
    }

    @Test(description = "test for checking is method find cube by size range", dataProvider = "scenario for finding cube by size range")
    public void findBySizeRange(double[] range, int result) {
        FindBySizeRange findByRange = new FindBySizeRange(range[0], range[1]);
        assertEquals(repository.query(findByRange).size(), result);
    }

    @DataProvider(name = "removing scenario")
    public Object[][] removeData() {
        return new Object[][]{
                {1, 2},
                {0, 3},
                {2, 2},
                {3, 2},
                {-1000, 3}
        };
    }

    @Test(description = "test for removing cubes from the map", dataProvider = "removing scenario")
    public void removeTest(long id, int result) {
        fillInfo();
        RemoveById remove = new RemoveById(id);
        repository.query(remove);
        assertEquals(testMap.size(), result);
    }

    @DataProvider(name = "updating scenario")
    public Object[][] updateData() {
        fillInfo();
        Cube cube1 = new Cube(new double[][]{{0, 0, 0}, {0, 4, 0}, {4, 4, 0}, {4, 0, 0}, {0, 0, 4}, {0, 4, 4}, {4, 4, 4}, {4, 0, 4}}, "cube1");
        cube1.setId(1);
        CubeRegistrar cubeRegistrar = new CubeRegistrar(cube1);
        return new Object[][]{
                {new Object[]{new double[]{0, 0, -4, 0, 4, -4, 4, 4, -4, 4, 0, -4}, new int[]{0, 1, 2, 3}, cube1}, 1},
                {new Object[]{new double[]{4, 4, 0, 4, 0, 0, 0, 0, -4, 0, 4, -4, 4, 4, -4, 4, 0, -4}, new int[]{0, 1}, cube1}, 1},
                {new Object[]{new double[]{4, 3.23, 2.36, 0, 3.23, 2.36, 0, -2.36, 3.23, 4, -2.36, 3.23, 4, 0.88, 5.59, 0, 0.88, 5.59}, new int[]{0, 3}, cube1}, 1},
                {new Object[]{new double[]{5, -4, 0, 0, -4, 0, 0, 0, -4, 4, 0, -4, 0, -4, -4}, new int[]{0, 1}, cube1}, 0},
                {new Object[]{new double[]{2, -2, 0, 4, 0, 0, 2, 2, 0, 0, 0, 2.83, 2, -2, 2.83, 4, 0, 2.83, 2, 2, 2.83}, new int[]{0}, cube1}, 1},
                {new Object[]{new double[]{2, -2, 0, 4, 0, 0, 2, 2, 0, 0, 0, 2.83, 2, -2, 2.83, 4, 0, 2.83, 2, 1, 2.83}, new int[]{0}, cube1}, 0},
                {new Object[]{new double[]{2, -2, 0, 4, 0, 0, 2, 2, 0, 0, 0, 2.83, 2, -2, 2.83, 4, 0, 2.83, 2, 1, 2.83}, new int[]{0, 1, 2}, cube1}, 0},
                {new Object[]{new double[]{2, -2, 0, 4, 0, 0, 2, 2, 0, 0, 0, 2.83, 2, -2, 2.83, 4, 0, 2.83, 2, 1, 2.83}, new int[]{0, 1, 2, 2}, cube1}, 0},
                {new Object[]{new double[]{2, -2, 0, 4, 0, 0, 2, 2, 0, 0, 0, 2.83, 2, -2, 2.83, 4, 0, 2.83, 2, 1}, new int[]{1}, cube1}, 0},
                {new Object[]{new double[]{2, -2, 0}, new int[]{1}, cube1}, 0},
        };
    }

    @Test(description = "test for updating cube", dataProvider = "updating scenario")
    public void updateTest(Object[] data, int result) {
        UpdateCube updateCube = new UpdateCube((double[]) data[0], (int[]) data[1], (Cube) data[2]);
        List size = repository.query(updateCube);
        assertEquals(size.size(), result);
    }

    @DataProvider(name = "scenario for sort")
    public Object[][] sortData() {
        fillInfo();
        return new Object[][]{
                {"NAME", 3},
                {"X", 3},
                {"Y", 3},
                {"Z", 3},
                {"ID", 3}
        };
    }

    @Test(description = "test for sort specification", dataProvider = "scenario for sort")
    public void sortTest(String data, int result){
        CubeComparator comparator = new CubeComparator(data);
        assertEquals(repository.query(comparator).size(),result);
    }

}
