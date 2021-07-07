package by.training.task05.service.update;

import by.training.task05.bean.Cube;
import by.training.task05.service.cubeimpl.CubeSpecification;

public interface UpdateSpecification extends CubeSpecification {
    Cube update();
}
