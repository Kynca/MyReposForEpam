package by.training.classes04.bean;

/**
 * helper class which stores information about result of search and different result of
 * implementation on controller class. If controller return status true View takes this information to
 * print the result for User
 */
public class Repository {

    double result;

    Bill bill;

    private static final Repository instance=new Repository();

    private Repository(){}

    public static Repository getInstance() {
        return instance;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
