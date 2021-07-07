package by.training.task05.service.find;

import by.training.task05.bean.Cube;

import java.util.Map;

public class FindById implements FindSpecification {

    long id;

    public FindById(long id) {
        this.id = id;
    }

    @Override
    public boolean isSpecified(Map.Entry entry) {
        Cube cube = (Cube) entry.getKey();
        return id == cube.getId();
    }

}
