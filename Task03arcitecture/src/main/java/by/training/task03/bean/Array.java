package by.training.task03.bean;

import java.util.Arrays;

public class Array {
    double array[];

    public Array(int lenght){
        this.array=new double[lenght];
    }

    public void setElement(int i,double element){
        array[i]=element;
    }
    public double getElement(int i){
        return array[i];
    }
    public int getLenght(){
        return array.length;
    }
     public  void swap(int i,int j){
         double temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    public double[] getArray() {
        return Arrays.copyOf(array,getLenght());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array array1 = (Array) o;
        return Arrays.equals(array, array1.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return "Array{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

}
