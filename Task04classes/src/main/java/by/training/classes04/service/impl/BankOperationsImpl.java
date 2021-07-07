package by.training.classes04.service.impl;

import by.training.classes04.bean.Bank;
import by.training.classes04.bean.Bill;
import by.training.classes04.bean.Client;
import by.training.classes04.dao.factory.DaoFactory;
import by.training.classes04.dao.impl.DaoImpl;
import by.training.classes04.service.BankOperations;
import by.training.classes04.service.ComparatorBill;
import by.training.classes04.service.Generator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * implements interface BankOperation
 */
public class BankOperationsImpl implements BankOperations {

    static final Logger serviceLogger = LogManager.getLogger("ServiceLog");

    Bank bank = Bank.getInstance();
    DaoFactory factory = DaoFactory.getInstance();
    DaoImpl impl = factory.getDaoImpl();

    /**
     * Validate name and passId, if data is correct create client by the specified parameters, and adds it to Bank
     * and save new client on file
     * @param data information required for creating user(name. passId, num of bills, billNum and sum on Bill), separated by ','
     * @return true if client created, false if not
     */
    @Override
    public boolean createUser(String data) {
        String name;
        String passportId;
        String[] clientInfo = data.split(",");
        int amountOfBills;
        name = clientInfo[0];
        String clientInfo1 = name + " ";

        if (name.length() < 2) {
            serviceLogger.info("User input incorrect name " + clientInfo[0]);
            return false;
        }

        passportId = clientInfo[1].toUpperCase();
        if (passportId.trim().length() != 6) {
            serviceLogger.info("User input incorrect passId=" + passportId);
            return false;
        }
        clientInfo1 += passportId + " ";

        Client client = new Client(name, passportId);
        for (Client client1 : bank.getListOfClient()) {
            if (client1.getPassportId().equals(passportId)) {
                serviceLogger.info("Client is already exist");
                return false;
            }
        }

        amountOfBills = Integer.parseInt(clientInfo[2]);
        clientInfo1 += amountOfBills + " ";

        for (int i = 0; i < amountOfBills; i++) {
            double sum = Double.parseDouble(clientInfo[i + 3]);
            Bill bill = new Bill(sum);
            client.addBill(bill);
            clientInfo1 += sum + " " + bill.getBillNum() + " ";
        }
        bank.addClient(client);
        serviceLogger.info("Client added");
        impl.writeFile("text.txt", clientInfo1);
        return true;
    }

    /**
     * method use cycle for go throw all of bills on Bank.listOfBills and sums money from
     * thees bills if it meet the condition
     * @param type type of sum
     * @return double result of calculation
     */
    @Override
    public double sumBills(int type) {
        if (type < 0 || type > 3) {
            return 0;
        }
        double allMoney = 0;
        switch (type) {
            case 1:
                for (Bill bill : bank.getListOfBills()) {
                    allMoney += bill.getMoney();
                }
                break;
            case 2:
                for (Bill bill : bank.getListOfBills()) {
                    if (bill.getMoney() < 0) {
                        allMoney += bill.getMoney();
                    }
                }
                break;
            case 3:
                for (Bill bill : bank.getListOfBills()) {
                    if (bill.getMoney() > 0) {
                        allMoney += bill.getMoney();
                    }
                }
                break;
        }
        serviceLogger.info("result of sum bills is " + allMoney);
        return allMoney;
    }

    /**
     * checks if this billNum exist, if it is get it from the list and return it
     * @param billNum parameter by which the Bill will be searched
     * @return null if bill wasn't founded, Bill if it is
     */
    @Override
    public Bill searchBill(int billNum) {
        final int MAX_BILL_NUM = 1000;
        final int MIN_BILL_NUM = 0;
        if (billNum < MIN_BILL_NUM || billNum > MAX_BILL_NUM) {
            return null;
        }

        if (!bank.checkList(billNum)) {
            return null;
        }

        for (Bill bill : bank.getListOfBills()) {
            if (bill.getBillNum() == billNum) {
                return bill;
            }
        }
        return null;
    }

    /**
     * checks if passportId is correct and if client with it exist gets his listOfBills
     * then use cycle foreach to go throw it and sum unblocked bill money
     * @param passportId parameter by which the Client will be searched
     * @return double calculated sum of clients unblocked bills
     */
    @Override
    public double sumClientBill(String passportId) {
        int allMoney = 0;

        if (passportId.trim().length() != 6) {
            return Integer.MIN_VALUE;
        }

        Client client = bank.getClient(passportId);

        if (client == null) {
            return Integer.MIN_VALUE;
        }

        System.out.println(client.getName());
        for (Bill bill : client.getBillList()) {
            if (!bill.isBlocked()) {
                allMoney += bill.getMoney();
            }
        }
        serviceLogger.info("result of calculation sumClientBill is " + allMoney);
        return allMoney;
    }

    /**
     * sorts bills via Comparator on defined parameter
     * @param type type of sort
     * @return sorted List<Bill></Bill>
     */
    @Override
    public List<Bill> sort(int type) {
        if (type == 2 || type == 1) {
            Collections.sort(bank.getListOfBills(), new ComparatorBill(type));
        }
        return bank.getListOfBills();
    }

    /**
     * fill Bank with information about client and Bills
     * calls dao function readFile and if file is empty calls method from Generator class
     * which fills Bank with random data
     */
    @Override
    public void initialise() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoImpl dao = daoFactory.getDaoImpl();

        String data = dao.readFile("text.txt");
        if (data == null || data.length() < 1) {
            Generator generator = new Generator();
            generator.generator();
        } else {
            String[] clientsInfo = data.split(",");
            for (int i = 0; i < clientsInfo.length; i++) {
                String[] clientInfo = clientsInfo[i].split(" ");
                Client client = new Client(clientInfo[0], clientInfo[1]);
                int amountOfBills = Integer.parseInt(clientInfo[2]);
                for (int j = 0; j < amountOfBills * 2; j = j + 2) {
                    Bill bill = new Bill(Double.parseDouble(clientInfo[j + 3]), Integer.parseInt(clientInfo[j + 4]));
                    client.addBill(bill);
                }
                bank.addClient(client);
            }
        }
    }

}

