package by.training.task08xml.view;

import by.training.task08xml.bean.Tariff;
import by.training.task08xml.bean.TariffWithMinutes;
import by.training.task08xml.bean.TariffWithoutMinutes;
import by.training.task08xml.controller.console.Controller;

import java.util.List;
import java.util.Scanner;

public class View {
    private Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        System.out.println("1. Dom Parser\n2. Sax Parser\n3. Stax Parser\n4. Exit");
        String request = "PARSE~";
        boolean flag = true;
        while (flag) {
            flag = false;
            switch (scanner.next()) {
                case "1":
                    request += "1~";
                    break;
                case "2":
                    request += "2~";
                    break;
                case "3":
                    request += "3~";
                    break;
                case "4":
                    return;
                default:
                    System.out.println("incorrect input, try again");
                    flag = true;
                    continue;
            }
            System.out.println("Enter filename of parsed xml file");
            scanner.nextLine();
            request += scanner.nextLine();
            List<Tariff> tariffs = controller.executeTask(request);
            print(tariffs);
        }
    }

    private void print(List<Tariff> tariffs) {
        System.out.printf("%-15s | %-15s | %-8s | %-10s | %-14s | %-14s | %-9s | %-15s | %-14s | %-12s | %-10s | %-10s |%-10s|\n",
                "name", "operator name", "payroll", "date", "free internet", "wifi hosting", "favourite", "subscription",
                "accumulation", "free within", "free out", "within", "out of");
        for (Tariff tariff : tariffs) {
            String name = tariff.getName();
            String operatorName = tariff.getOperatorName();
            String payroll = "" + tariff.getPayroll();
            String date = tariff.getDate();
            String freeInet = tariff.getParameters().getFreeInternet();
            String wifiHost = tariff.getParameters().getWifiHosting();
            String favourite;
            if (tariff.getParameters().isFavorite()) {
                favourite = "+";
            } else {
                favourite = "-";
            }

            String subscription;
            if (tariff.getParameters().getSubscription() == null || tariff.getParameters().getSubscription().equals("false")) {
                subscription = "-";
            } else {
                subscription = tariff.getParameters().getSubscription();
            }

            String accumulation;
            if (tariff.getParameters().isAccumulation()) {
                accumulation = "+";
            } else {
                accumulation = "-";
            }
            String freeWithin = null;
            String freeOut = null;
            String within = null;
            String out = null;
            if (tariff instanceof TariffWithMinutes) {
                freeWithin = ((TariffWithMinutes) tariff).getFreeWithin();
                freeOut = ((TariffWithMinutes) tariff).getFreeOut();
                out = "-";
                within = "-";
            } else if (tariff instanceof TariffWithoutMinutes) {
                freeWithin = "-";
                freeOut = "-";
                within = "" + ((TariffWithoutMinutes) tariff).getWithinNetwork();
                out = "" + ((TariffWithoutMinutes) tariff).getOutOfNetwork();
            }
            System.out.printf("%-15s | %-15s | %-8s | %-10s | %-14s | %-14s | %-9s | %-15s | %-14s | %-12s | %-10s | %-10s |%-10s|\n",name,operatorName,payroll,
                    date,freeInet,wifiHost,favourite,subscription,accumulation,freeWithin,freeOut,within,out);

        }
    }
}
