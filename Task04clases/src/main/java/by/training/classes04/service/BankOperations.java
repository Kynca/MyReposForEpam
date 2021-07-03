package by.training.classes04.service;

import by.training.classes04.bean.Bill;

import java.util.List;

/**
 * interface with methods which used in service layer
 */
public interface BankOperations {
    boolean createUser(String clientInfo);

    double sumBills(int type);

    double sumClientBill(String pass);

    Bill searchBill(int billNum);

    List sort(int type);

    void initialise();
}
