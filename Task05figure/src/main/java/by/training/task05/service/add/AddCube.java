package by.training.task05.service.add;

import by.training.task05.bean.Cube;
import by.training.task05.bean.CubeRegistrar;
import by.training.task05.service.cubeimpl.CubeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Set;

public class AddCube implements AddSpecification {

    static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    Cube cube;

    public AddCube(double[][] points) {
        cube = new Cube(points);
        cube.setName("Cube");
    }

    public AddCube(double[][] points, String name) {
        cube = new Cube(points, name);
    }

    @Override
    public Cube addCube(Map map, Set set) {
        CubeValidator validator = new CubeValidator(cube.getPoints());
        if (validator.isCube()) {
            CubeRegistrar registrar = new CubeRegistrar(cube);
            long id = idGenerator(set);
            cube.setId(id);
            if (cube.getName().equals("Cube")) {
                cube.setName("Cube" + id);
            }
            cube.notifyAllObservers();
            map.put(cube, registrar);
            return cube;
        } else {
            serviceLog.info(cube.getPoints().toString() + "don't make a cube");
            return null;
        }
    }

    private long idGenerator(Set set) {
        long setSize = set.size();
        if (set.contains(setSize + 1)) {
            while (set.contains(setSize)) {
                setSize--;
            }
        }
        set.add(setSize);
        return setSize;
    }

}
