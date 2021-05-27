package by.epam.task2_branching;

public class RealNumberComparison {
    /**
     * method with check is entered number in descending order
     * @param fistNumber
     * @param secondNumber
     * @param thirdNumber
     * @return String, if numbers meet the conditions return String with doubled numbers
     * in other case return module of this numbers
     */
    public String numberCompare(double fistNumber, double secondNumber, double thirdNumber){
        if(fistNumber>secondNumber&&secondNumber>thirdNumber) {
            fistNumber =Math.pow(fistNumber,2);
            secondNumber =Math.pow(secondNumber,2);
            thirdNumber =Math.pow(thirdNumber,2);
            return "fistNumber=" + fistNumber + " secondNumber=" + secondNumber + " thirdNumber=" + thirdNumber;
        }else {
         return "fistNumber=" + Math.abs(fistNumber) + " secondNumber=" + Math.abs(secondNumber) + " thirdNumber=" + Math.abs(thirdNumber);
        }
    }
}
