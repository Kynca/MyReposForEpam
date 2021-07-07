package by.training.task05.service.find;

import by.training.task05.bean.CubeRegistrar;

import java.util.Map;

public class FindBySize implements FindSpecification{

    private double size;

    public FindBySize(double size){
        this.size=size;
    }

    @Override
    public boolean isSpecified(Map.Entry entry) {
        final double LIMIT = 0.01;
        CubeRegistrar registrar = (CubeRegistrar) entry.getValue();
        return Math.abs(size-registrar.getSize())<LIMIT;
    }

}
