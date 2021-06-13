package by.training.task03.service.factory;

import by.training.task03.service.*;
import by.training.task03.service.impl.*;

public final class ServiceFactory {
    private static final ServiceFactory instance= new ServiceFactory();
    private static ArrayService arrayServiceImpl =new ArrayServiceImpl();
    private static MatrixService matrixServiceImpl=new MatrixServiceImpl();


    private  ServiceFactory(){ }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public static MatrixService getMatrixService() {
        return matrixServiceImpl;
    }

    public static ArrayService getArrayService() {
        return arrayServiceImpl;
    }

}
