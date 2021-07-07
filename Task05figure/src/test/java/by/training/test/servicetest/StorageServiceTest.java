package by.training.test.servicetest;

import by.training.task05.bean.Cube;
import by.training.task05.bean.CubeRegistrar;
import by.training.task05.bean.CubeStorage;
import by.training.task05.service.exception.ServiceException;
import by.training.task05.service.storageservice.StorageServiceImpl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class StorageServiceTest {
    CubeStorage cubeStorage = CubeStorage.getInstance();
    StorageServiceImpl storageService = new StorageServiceImpl();

    @DataProvider(name = "reading scenario")
    public Object[][] fillData() {
        return new Object[][]{
                {"data/cubeData.txt", 6}
        };
    }

    @Test(description = "testing read and filling storage with data", dataProvider = "reading scenario")
    public void fillStorageTest(String filename, int result) throws ServiceException {
        storageService.fillStorage(filename);
        assertEquals(cubeStorage.getCubeStorage().size(), result);
    }

    @DataProvider(name = "writing scenario")
    public Object[][] writeData() {
        File file = new File("data/check.txt");
        file.delete();
        Cube cube = new Cube(new double[][]{{0,0,0},{4,0,0},{4,4,0},{0,4,0},{0,0,4},{4,0,4},{4,4,4},{0,4,4}});
        CubeRegistrar registrar = new CubeRegistrar(cube);
        cubeStorage.getCubeStorage().put(cube,registrar);
        return new Object[][]{
                {"data/check.txt", true}
        };
    }

    @Test(description = "testing for writing in file ", dataProvider = "writing scenario")
    public void writeTest(String filename, boolean result) throws ServiceException {
     File file = new File("data/check.txt");
     storageService.saveStorage(filename);
     assertEquals(file.length()>0,result);
    }
    }
