package by.training.task03.bean;

import java.util.ArrayList;
import java.util.List;

public final class Repository {
    private static final Repository instance = new Repository();
    private List<double[][]> matrixList=new ArrayList<>();
    Array array;

    private Repository() {
    }

    public static Repository getInstance() {
        return instance;
    }
    public void addMatrix(double[][]matrix,int position){
        matrixList.add(position, matrix);
    }
    public void removeALl(int remove) {
        if (remove == 1) {
            matrixList.remove(0);
        } else {
            matrixList.remove(0);
            matrixList.remove(1);
        }
    }

    public void setArray(Array array){
        this.array=array;
    }

    public Array getArray(){
        return array;
    }


    public double[][] getMatrix(int i){
        return matrixList.get(i);
    }
}
