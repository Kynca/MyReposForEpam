package by.training.task05.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Cube implements Notifier {
    private List observers;
    private Point[] pointArray = new Point[8];
    long id;
    String name;

    public Cube(double[][] points) {
        for (int i = 0; i < 8; i++) {
            pointArray[i] = new Point(points[i][0], points[i][1], points[i][2]);
        }
        observers = new ArrayList();
    }

    public Cube(double[][] points, String name) {
        for (int i = 0; i < 8; i++) {
            pointArray[i] = new Point(points[i][0], points[i][1], points[i][2]);
        }
        this.name = name;
        observers = new ArrayList();
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        int i = observers.indexOf(obs);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyAllObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer obs = (Observer) observers.get(i);
            obs.update(pointArray);
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        notifyAllObservers();
    }

    public void changeData(Point[] pointArray) {
        this.pointArray = Arrays.copyOf(pointArray, pointArray.length);
        notifyAllObservers();
    }

    public Point getPoint(int i) {
        return pointArray[i];
    }

    public double getPointX(){
        return pointArray[0].x;
    }

    public double getPointY(){
        return pointArray[0].y;
    }
    public double getPointZ(){
        return pointArray[0].z;
    }

    public List getObservers() {
        return observers;
    }

    public Point[] getPoints() {
        return Arrays.copyOf(pointArray, pointArray.length);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return id == cube.id &&
                Objects.equals(observers, cube.observers) &&
                Arrays.equals(pointArray, cube.pointArray) &&
                Objects.equals(name, cube.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(observers, id, name);
        result = 31 * result + Arrays.hashCode(pointArray);
        return result;
    }

    @Override
    public String toString() {
        return "Cube{" +
                "observers=" + observers +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public class Point {
        private double x;
        private double y;
        private double z;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }
    }

}
