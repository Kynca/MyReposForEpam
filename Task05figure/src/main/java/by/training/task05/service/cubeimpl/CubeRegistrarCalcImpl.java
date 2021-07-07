package by.training.task05.service.cubeimpl;

import by.training.task05.bean.Cube;

public class CubeRegistrarCalcImpl implements CubeRegistrarCalc {

    @Override
    public double volumeCalc(double size) {
        return size * size * size;
    }

    @Override
    public double areaCalc(double size) {
        return 6 * size * size;
    }

    @Override
    public boolean isOnPlane(Cube.Point[] arrayPoint) {
        if (planeCheck(arrayPoint[0], arrayPoint[1], arrayPoint[4])) {
            return true;
        }
        if (planeCheck(arrayPoint[0], arrayPoint[1], arrayPoint[2])) {
            return true;
        }
        if (planeCheck(arrayPoint[0], arrayPoint[3], arrayPoint[4])) {
            return true;
        }
        if (planeCheck(arrayPoint[6], arrayPoint[2], arrayPoint[1])) {
            return true;
        }
        if (planeCheck(arrayPoint[6], arrayPoint[2], arrayPoint[3])) {
            return true;
        }
        return planeCheck(arrayPoint[6], arrayPoint[5], arrayPoint[4]);
    }



    private boolean planeCheck(Cube.Point point1, Cube.Point point2, Cube.Point point3) {
        if (point1.getX() == point2.getX() && point2.getX() == point3.getX() && point3.getX() == 0) {
            return true;
        } else if (point1.getY() == point2.getY() && point2.getY() == point3.getY() && point3.getY() == 0) {
            return true;
        } else if (point1.getZ() == point2.getZ() && point2.getZ() == point3.getZ() && point3.getZ() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double sizeCalc(Cube.Point point1, Cube.Point point2) {
        double x = point1.getX() + point2.getX();
        double y = point1.getY() + point2.getY();
        double z = point1.getZ() + point2.getZ();
        return Math.sqrt(x * x + y * y + z * z);
    }

}
