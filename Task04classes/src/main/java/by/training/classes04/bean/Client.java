package by.training.classes04.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * Class Client have name of client, passportId of client(id) and list of bills which client has
 * this class have 1 constructor and getters and 1 method add Bill to list of the Bill and to String method
 */
public class Client {
    Bank bank=Bank.getInstance();
    private String name;
    private String passportId;
    private List<Bill> billList = new LinkedList<>();

    public Client(String name, String passportId) {
        this.name = name;
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public String getPassportId() {
        return passportId;
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public void addBill(Bill bill) {
        if (bill != null) {
            billList.add(bill);
            bank.addBill(bill);
        }
    }

    @Override
    public String toString() {
        return "Client " + name  +
                ", passportId = " + passportId + ", Amount of bills=" + billList.size();
    }

}
