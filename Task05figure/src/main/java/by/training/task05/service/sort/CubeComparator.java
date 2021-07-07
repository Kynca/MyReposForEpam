package by.training.task05.service.sort;

import by.training.task05.bean.Cube;

import java.util.Comparator;

public class CubeComparator implements SortSpecification{

    private CubeComparators comparator;

    public CubeComparator(String name){
        switch (name){
            case "ID":
                comparator = CubeComparators.ID;
                break;
            case "NAME" :
                comparator = CubeComparators.NAME;
                break;
            case "X" :
                comparator = CubeComparators.X;
                break;
            case "Y":
                comparator = CubeComparators.Y;
                break;
            case "Z":
                comparator = CubeComparators.Z;
                break;
            default:
                comparator = CubeComparators.ID;
        }
    }

    @Override
    public Comparator<Cube> takeComparator() {
        return comparator.getComparator();
    }

    enum CubeComparators{
        ID(Comparator.comparing(Cube::getId)),
        NAME(Comparator.comparing(Cube::getName).thenComparing(Cube::getId)),
        X(Comparator.comparing(Cube::getPointX).thenComparing(Cube::getId)),
        Y(Comparator.comparing(Cube::getPointY).thenComparing(Cube::getId)),
        Z(Comparator.comparing(Cube::getPointZ).thenComparing(Cube::getId));

        private Comparator<Cube> comparator;

        CubeComparators(Comparator<Cube> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Cube> getComparator() {
            return comparator;
        }
    }
}
