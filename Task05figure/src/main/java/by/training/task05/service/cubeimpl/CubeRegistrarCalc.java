package by.training.task05.service.cubeimpl;

import by.training.task05.bean.Cube;

public interface CubeRegistrarCalc {
    double volumeCalc(double size);
    double areaCalc(double size);
    boolean isOnPlane(Cube.Point[] pointArray);
    double sizeCalc(Cube.Point point1, Cube.Point point2);
}
