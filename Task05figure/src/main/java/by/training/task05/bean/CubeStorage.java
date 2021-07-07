package by.training.task05.bean;

import java.util.*;

public class CubeStorage {

    private static final CubeStorage instance = new CubeStorage();

    private Map<Cube, CubeRegistrar> cubeStorage = new HashMap<>();

    private Set<Long> setId = new HashSet<>();

    private CubeStorage() {
    }

    public Map<Cube, CubeRegistrar> getCubeStorage() {
        return cubeStorage;
    }

    public Set<Long> getSetId(){
        return setId;
    }

    @Override
    public String toString() {
        return "CubeStorage{" +
                "cubeStorage=" + cubeStorage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeStorage that = (CubeStorage) o;
        return Objects.equals(cubeStorage, that.cubeStorage) &&
                Objects.equals(setId, that.setId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cubeStorage, setId);
    }

    public static CubeStorage getInstance() {
        return instance;
    }
}
