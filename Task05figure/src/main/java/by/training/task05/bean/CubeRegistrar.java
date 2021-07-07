package by.training.task05.bean;

import by.training.task05.service.cubeimpl.CubeRegistrarCalcImpl;

import java.util.Arrays;

public class CubeRegistrar implements Observer {
    Notifier notifier;
    Cube.Point[] pointArray;
    double cubeArea;
    double cubeVolume;
    double size;
    boolean isOnCoordinatePlane;

    public CubeRegistrar(Notifier notifier) {
        this.notifier = notifier;
        notifier.addObserver(this);
    }

    @Override
    public void update(Cube.Point[] newArray) {
        CubeRegistrarCalcImpl registrar = new CubeRegistrarCalcImpl();
        pointArray = Arrays.copyOf(newArray, newArray.length);
        size = registrar.sizeCalc(pointArray[0], pointArray[1]);
        cubeArea = registrar.areaCalc(size);
        cubeVolume = registrar.volumeCalc(size);
        isOnCoordinatePlane = registrar.isOnPlane(pointArray);
    }

    @Override
    public String toString() {
        return "CubeRegistrar{" +
                "pointArray=" + Arrays.toString(pointArray) +
                ", cubeArea=" + cubeArea +
                ", cubeVolume=" + cubeVolume +
                ", size=" + size +
                ", isOnCoordinatePlane=" + isOnCoordinatePlane +
                '}';
    }

    public double getCubeArea() {
        return cubeArea;
    }

    public double getCubeVolume() {
        return cubeVolume;
    }

    public double getSize() {
        return size;
    }

    public boolean isOnCoordinatePlane() {
        return isOnCoordinatePlane;
    }
}
