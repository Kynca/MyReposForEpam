package by.epam.task2_branching;

/**
 * simple function for finding greatest number
 */
public class GreatestNumber {
    public int numberComparator(int num1,int num2){
        if(num1==num2)return num1;
        return num1>num2?num1:num2;
    }
}
