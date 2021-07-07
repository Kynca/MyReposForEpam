package by.training.task05.service.find;

import by.training.task05.service.cubeimpl.CubeSpecification;

import java.util.Map;

public interface FindSpecification extends CubeSpecification {
   boolean isSpecified(Map.Entry entry);
}
