package by.training.classes04.service;

import by.training.classes04.bean.Bill;

import java.util.Comparator;

/**
 * Class helper for function sort in BankOperationImpl class implements interface Comparator<>
 * It allows sort listOfBills on chosen parameter
 */
public class ComparatorBill implements Comparator<Bill> {
    int type;
    public ComparatorBill(int type){
        this.type=type;
    }

    /**
     * @param o1 1st object to comparisons
     * @param o2 2nd object to comparisons
     * @return int result of method compare
     */
    @Override
    public int compare(Bill o1, Bill o2) {
        if (type == 1) {
            return Integer.compare(o1.getBillNum(),o2.getBillNum());
        }else{
            return Double.compare(o1.getMoney(),o2.getMoney());
        }
    }
}
