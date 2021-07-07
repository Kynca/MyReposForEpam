package by.training.task05.service.find;

import by.training.task05.bean.Cube;

import java.util.Map;

public class FindByName implements FindSpecification {

    private String searchedName;

    public FindByName(String searchedName) {
        this.searchedName = searchedName;
    }

    @Override
    public boolean isSpecified(Map.Entry entry) {
        Cube cube = (Cube) entry.getKey();
        return searchedName.equals(cube.getName());
    }

}
