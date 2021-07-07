package by.training.task05.service.update;

import by.training.task05.bean.Cube;
import by.training.task05.service.cubeimpl.CubeValidator;
import by.training.task05.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class UpdateCube implements UpdateSpecification {

    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    Cube cube;
    private Cube.Point[] points;
    private int[] pointIndexes;
    private final int[][] WRONG_INDEX = {{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 5}, {6, 7, 5, 2, 3, 7, 4, 6, 7, 4, 5, 6, 5, 4, 6, 7}};
    private final int[][] CORRECT_INDEX = {{0, 1, 4, 5}, {0, 1, 2, 3}, {0, 3, 4, 7}, {1, 2, 4, 6}, {2, 3, 6, 7}, {4, 5, 6, 7}};

    public UpdateCube(double[] points, int[] pointIndexes, Cube cube) {
        Arrays.sort(pointIndexes);
        this.pointIndexes = pointIndexes;
        int lenght = 8 - pointIndexes.length;
        double[][] newPoints = new double[8][3];

        if (points.length < lenght * 3) {
            this.cube = null;
        } else {
            int m = 0;
            int n;
            for (int j = 0; j < points.length; j++) {
                n = j % 3;
                if (n == 0 && j != 0) {
                    m++;
                }
                newPoints[m][n] = points[j];
            }

            Cube tempCube = new Cube(newPoints);
            this.points = new Cube.Point[lenght];
            for (int i = 0; i < lenght; i++) {
                this.points[i] = tempCube.getPoint(i);
            }
            this.cube = cube;
        }
    }

    @Override
    public Cube update() {

        if(cube == null){
            return null;
        }
        Cube.Point[] newPoints = new Cube.Point[8];
        int length = pointIndexes.length;
        switch (length) {
            case 1:
                newPoints[0] = cube.getPoint(pointIndexes[0]);
                System.arraycopy(points, 0, newPoints, 1, 7);
                break;
            case 2:
                for (int i = 0; i < WRONG_INDEX.length; i++) {
                    if (pointIndexes[0] == WRONG_INDEX[0][i] && pointIndexes[1] == WRONG_INDEX[1][i]) {
                        return null;
                    }
                }
                newPoints[0] = cube.getPoint(pointIndexes[0]);
                newPoints[1] = cube.getPoint(pointIndexes[1]);
                System.arraycopy(points, 0, newPoints, 2, 6);
                break;
            case 4:
                boolean flag = false;
                for (int i = 0; i < CORRECT_INDEX.length; i++) {
                    flag = false;
                    int counter = 0;
                    for (int j = 0; j < 4; j++) {
                        if (pointIndexes[j] == CORRECT_INDEX[i][j]) {
                            counter++;
                            flag = true;
                            continue;
                        }
                        if(counter ==4){
                            break;
                        }
                    }
                    if(flag){
                        break;
                    }
                }
                if (!flag) {
                    return null;
                }
                for (int i = 0; i < 8; i++) {
                    if (i < 4) {
                        newPoints[i] = cube.getPoint(pointIndexes[i]);
                    } else {
                        newPoints[i] = points[i - 4];
                    }
                }
                break;
            default:
                return null;
        }
        CubeValidator validator = new CubeValidator(newPoints);
        if (validator.isCube()) {
            cube.changeData(newPoints);
            serviceLog.info("cube updated");
            return cube;
        }

        return null;
    }
}
