package by.training.task05.service.storageservice;

import by.training.task05.bean.Cube;
import by.training.task05.bean.CubeStorage;
import by.training.task05.service.Repository;
import by.training.task05.dao.DaoFactory;
import by.training.task05.dao.DaoImpl;
import by.training.task05.dao.exception.DaoException;
import by.training.task05.service.add.AddCube;
import by.training.task05.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StorageServiceImpl implements StorageService {

    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    CubeStorage cubeStorage = CubeStorage.getInstance();
    DaoFactory daoFactory = DaoFactory.getInstance();
    DaoImpl dao = daoFactory.getDaoImpl();

    @Override
    public boolean saveStorage(String filename) throws ServiceException {
        StringBuilder builder = new StringBuilder("");

        try {
            for (Cube cube : cubeStorage.getCubeStorage().keySet()) {
                Cube.Point[] cubeArray = cube.getPoints();
                for (int i = 0; i < cubeArray.length; i++) {
                    builder.append(cubeArray[i].getX() + " ");
                    builder.append(cubeArray[i].getY() + " ");
                    builder.append(cubeArray[i].getZ() + " ");
                }
                builder.append(cube.getName() + " ");
                dao.writeFile(filename, String.valueOf(builder));
                builder.delete(0, builder.length());
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public void fillStorage(String filename) throws ServiceException {

        Repository repository = Repository.getInstance();

        String dataFromFile;
        try {
            dataFromFile = dao.readFile(filename);
        } catch (DaoException e) {
            throw new ServiceException("cant read info from file", e);
        }

        double[][] cubePoints = new double[8][3];
        String cubesInfo[] = dataFromFile.split(",");
        for (int i = 0; i < cubesInfo.length; i++) {
            try {
                String[] cubeData = cubesInfo[i].split(" ");
                int dataLenght = cubeData.length;
                if (dataLenght < 24 || dataLenght > 25) {
                    serviceLog.error("incorrect data from file" + cubeData.toString());
                    continue;
                }
                int m = 0;
                int n;
                for (int j = 0; j < 24; j++) {
                    n = j % 3;
                    if (n == 0 && j != 0) {
                        m++;
                    }
                    cubePoints[m][n] = Double.parseDouble(cubeData[j]);
                }
                if (dataLenght == 24) {
                    AddCube addCube = new AddCube(cubePoints);
                    repository.query(addCube);
                } else if (dataLenght == 25) {
                    AddCube addCube = new AddCube(cubePoints, cubeData[24]);
                    repository.query(addCube);
                }
            } catch (NumberFormatException e) {
                serviceLog.error(cubesInfo.toString() + "skipped");
            }
        }

    }
}
