package by.training.task05.service.sort;

import by.training.task05.bean.Cube;
import by.training.task05.service.cubeimpl.CubeSpecification;

import java.util.Comparator;

public interface SortSpecification extends CubeSpecification {
    Comparator<Cube> takeComparator();
}
