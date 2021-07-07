package by.training.task05.service.find;

import by.training.task05.bean.CubeRegistrar;

import java.util.Map;

public class FindBySizeRange implements FindSpecification {

    private double minSize;
    private double maxSize;

    public FindBySizeRange(double minSize, double maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public boolean isSpecified(Map.Entry entry) {
        if(minSize > maxSize){
            return false;
        }
        CubeRegistrar registrar = (CubeRegistrar) entry.getValue();
        return minSize <= registrar.getSize() && registrar.getSize()<=maxSize;
    }

}
