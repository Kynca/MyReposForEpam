package by.training.classes04.bean;

/**
 * Class Bill have information about client bill(money-sum on bill, blocked - status of bill and billNum - id)
 * also this class have getters and setters and constructor and to String method
 */
public class Bill {
    Bank bank = Bank.getInstance();
    int MAX = 1000;
    private double money;
    private boolean blocked;
    private int billNum;

    public Bill(double money) {
        blocked = false;
        this.money = money;
        boolean check = true;
        while (check) {
            billNum = (int) (Math.random() * ++MAX);
            check = bank.checkList(billNum);
        }
    }

    public Bill(double money, int billNum) {
        blocked = false;
        this.money = money;
        this.billNum = billNum;
    }

    public double getMoney() {
        return money;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }

    public int getBillNum() {
        return billNum;
    }

    public void setMoney(double change) {
        money+=change;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billNum=" + billNum +
                ", money=" + money +
                '}';
    }

}
