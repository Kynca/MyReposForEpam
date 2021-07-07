package by.training.classes04.service;

import by.training.classes04.bean.Bank;
import by.training.classes04.bean.Bill;
import by.training.classes04.bean.Client;
import by.training.classes04.dao.factory.DaoFactory;
import by.training.classes04.dao.impl.DaoImpl;
import java.util.Random;

/**
 * Class which generate realistic data for Bank
 */
public class Generator {

    private Random random = new Random();
    private char[] arr_EN = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private String[] names = {"Ivan", "Sasha", "Ilya", "Dima", "Dasha", "Maxim", "Roma", "Marta", "Ira", "Nastya", "Ksenia", "Gleb", "Alina",
            "Nina", "Masha", "Olga"};
    private String name;
    private Bank bank = Bank.getInstance();

    /**
     * generate 20 Client and add it to Bank
     * name of client gets from names[], passId generates due to arr_EN and random number generator
     * then adds Client random amount of bills from 1 to 3 and fill it with random double sum
     */
    public void generator() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoImpl daoImpl = daoFactory.getDaoImpl();
        StringBuilder clientInfo = new StringBuilder("");
        for (int i = 0; i < 20; i++) {
            name = names[random.nextInt(names.length)];
            for (int j = 0; j < 2; j++) {
                clientInfo.append(arr_EN[random.nextInt(arr_EN.length)]);
            }
            for (int j = 0; j < 4; j++) {
                clientInfo.append(random.nextInt(10));
            }
            Client client = new Client(name, String.valueOf(clientInfo));

            double sum;
            int times = random.nextInt(3) + 1;
            clientInfo.append(" " + times);
            for (int j = 0; j < times; j++) {
                sum = (random.nextInt(2000) - random.nextInt(2400) + random.nextDouble()) * 100;
                sum = Math.round(sum) / 100.0;
                Bill bill = new Bill(sum);
                clientInfo.append(" " + sum + " " + bill.getBillNum());
                client.addBill(bill);
            }
            bank.addClient(client);
            daoImpl.writeFile("text.txt", name + " " + clientInfo);
            clientInfo.delete(0, clientInfo.length());
        }
    }

}
