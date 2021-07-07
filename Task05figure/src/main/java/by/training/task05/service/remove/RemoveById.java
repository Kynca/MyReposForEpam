package by.training.task05.service.remove;

import by.training.task05.bean.Cube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class RemoveById implements RemoveSpecification {

    static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    private long id;

    public RemoveById(long id) {
        this.id = id;
    }

    @Override
    public Cube remove(Set<Cube> cubeSet) {
        serviceLog.info(id);
        for (Cube cubes : cubeSet) {
            if (id == cubes.getId()) {
                return cubes;
            }
        }
        return null;
    }

}
