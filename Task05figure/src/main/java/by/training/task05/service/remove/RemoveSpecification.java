package by.training.task05.service.remove;

import by.training.task05.bean.Cube;
import by.training.task05.service.cubeimpl.CubeSpecification;

import java.util.Set;

public interface RemoveSpecification extends CubeSpecification {
    Cube remove(Set<Cube> cubeSet);
}
