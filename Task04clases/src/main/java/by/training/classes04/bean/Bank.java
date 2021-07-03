package by.training.classes04.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * class Bank stores all information about user and bills registered in this bank
 * and has common methods to add bill
 */
public class Bank {
    private static final Bank instance = new Bank();

    private Bank() {}

    private List<Client> listOfClient =new ArrayList<>();
    private List<Bill> listOfBills = new ArrayList<>();


    public List<Client> getListOfClient() {
        return listOfClient;
    }

    public List<Bill> getListOfBills() {
        return listOfBills;
    }

    public void addClient(Client client){
        listOfClient.add(client);
    }

    public void addBill(Bill bill){
        listOfBills.add(bill);
    }

    /**
     * checks if this billNum already exist in this bank
     * @param billNum num of bills(id)
     * @return boolean result of check
     */
    public boolean checkList(int billNum){
        for(Bill bills: listOfBills){
            if(bills.getBillNum()==billNum){
                return true;
            }
        }
        return false;
    }

    public Client getClient(String passport){
        Client client=null;
        for (Client client1:listOfClient) {
            if(client1.getPassportId().equals(passport)){
                client=client1;
            }
        }
        return client;
    }

    public static Bank getInstance() {
        return instance;
    }

    public int getSizeOfClient(){
        return listOfClient.size();
    }
}
