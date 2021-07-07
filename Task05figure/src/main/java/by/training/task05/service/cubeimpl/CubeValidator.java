package by.training.task05.service.cubeimpl;

import by.training.task05.bean.Cube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class CubeValidator {

    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    private final double EPSILON = 0.01;
    private Cube.Point[] points;

    public CubeValidator(Cube.Point[] points) {
        this.points = Arrays.copyOf(points, points.length);
    }

    public boolean isCube() {
        double sizeDistance;

        if (points.length != 8) {
            serviceLog.error(points.length + "!=8");
            return false;
        }

        sizeDistance = calcDistance(points[0], points[3]);
        serviceLog.info("cube size =" + sizeDistance);

        for (int i = 0; i < 6; i++) {
            if (i == 3) {
                continue;
            }
            double actualSize = calcDistance(points[i], points[i + 1]);
            if (Math.abs(sizeDistance - actualSize)  > EPSILON) {
                serviceLog.error("cant create a cube");
                return false;
            }
        }
        return checkDiagonal(points);
    }

    private double calcDistance(Cube.Point point1, Cube.Point point2) {
        double x = point1.getX() - point2.getX();
        double y = point1.getY() - point2.getY();
        double z = point1.getZ() - point2.getZ();
        return Math.sqrt(x * x + y * y + z * z);
    }

    private boolean checkDiagonal(Cube.Point[] points) {
        double diagonalSize = calcDistance(points[0], points[6]);
        if (diagonalSize - calcDistance(points[1], points[7]) > EPSILON) {
            return false;
        }
        if (diagonalSize - calcDistance(points[2], points[4]) > EPSILON) {
            return false;
        }
        if (diagonalSize - calcDistance(points[3], points[5]) > EPSILON) {
            return false;
        }
        return true;
    }
}
