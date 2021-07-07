package by.training.task05.service;

import by.training.task05.bean.Cube;
import by.training.task05.bean.CubeStorage;
import by.training.task05.service.cubeimpl.CubeSpecification;
import by.training.task05.service.add.AddSpecification;
import by.training.task05.service.find.FindSpecification;
import by.training.task05.service.remove.RemoveSpecification;
import by.training.task05.service.sort.SortSpecification;
import by.training.task05.service.update.UpdateSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Consumer;

public class Repository {

    private static final Logger serviceLog = LogManager.getLogger("ServiceLog");

    CubeStorage cubeStorage = CubeStorage.getInstance();

    private static final Repository instance = new Repository();

    private List<Cube> tempCube= new ArrayList<>();

    public List<Cube> query(CubeSpecification specification) {

        List<Cube> cubeList = new ArrayList<>();

        if (specification instanceof AddSpecification) {
           Cube cube = ((AddSpecification) specification).addCube(cubeStorage.getCubeStorage(), cubeStorage.getSetId());
            if(cube != null){
                cubeList.add(cube);
            }
        }

        if (specification instanceof RemoveSpecification) {
            cubeList = new ArrayList<>();
           Cube cube = ((RemoveSpecification) specification).remove(cubeStorage.getCubeStorage().keySet());
           if(cube == null){
               serviceLog.info("Cube not found");
           }else {
               cubeStorage.getSetId().remove(cube.getId());
               cubeStorage.getCubeStorage().remove(cube);
               cubeList.add(cube);
           }
        }

        if (specification instanceof UpdateSpecification) {
            Cube cube = ((UpdateSpecification) specification).update();
            if(cube != null){
                cubeList.add(cube);
            }else{
                serviceLog.error("can not update cube");
            }
        }

        if (specification instanceof FindSpecification) {
            for (Map.Entry entry : cubeStorage.getCubeStorage().entrySet()) {
                if (((FindSpecification) specification).isSpecified(entry)) {
                    cubeList.add((Cube) entry.getKey());
                    tempCube.add(0, (Cube) entry.getKey());
                }
            }
        }

        if (specification instanceof SortSpecification) {
            for (Map.Entry entry : cubeStorage.getCubeStorage().entrySet()) {
                cubeList.add((Cube) entry.getKey());
            }
            Comparator<Cube> comparator;
            comparator = ((SortSpecification) specification).takeComparator();
            Consumer consumer = (list) -> Collections.sort((ArrayList) list, comparator);
            consumer.accept(cubeList);
        }

        return cubeList;
    }

    private Repository() {
    }

    public static Repository getInstance() {
        return instance;
    }

    public Cube getTempCube() {
        return tempCube.get(0);
    }

    public void remove(){
        tempCube.remove(0);
    }
}
