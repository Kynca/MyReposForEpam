package by.training.task01.ex6runner;
import by.training.task01.ex6.MilkAndCans;

public class MilkRunner {
    public static void main(String[] args) {
        MilkAndCans mac=new MilkAndCans();
        System.out.println("How many small Cans?");
        int smallCans=mac.canInput(80);
        System.out.println("How many big Cans?");
        int bigCans=mac.canInput(80);
        System.out.println(mac.bigCanCalculation(smallCans,bigCans)+" milk in " +bigCans+" bigCans");
    }
}
