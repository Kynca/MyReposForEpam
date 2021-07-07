package by.training.task05.service.find;

import by.training.task05.bean.CubeRegistrar;

import java.util.Map;

public class FindByOnPlane implements FindSpecification{

    @Override
    public boolean isSpecified(Map.Entry entry) {
        CubeRegistrar registrar = (CubeRegistrar) entry.getValue();
        return registrar.isOnCoordinatePlane();
    }

}
