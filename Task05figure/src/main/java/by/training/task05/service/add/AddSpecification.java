package by.training.task05.service.add;

import by.training.task05.bean.Cube;
import by.training.task05.service.cubeimpl.CubeSpecification;

import java.util.Map;
import java.util.Set;

public interface AddSpecification extends CubeSpecification {
     Cube addCube(Map map, Set set);
}
