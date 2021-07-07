package by.training.classes04.view;

import by.training.classes04.bean.MessageManager;
import by.training.classes04.bean.Repository;
import by.training.classes04.controller.Controller;

import java.util.Scanner;

/**
 * Class View have all menus for user and contact with him
 * via View controller knows which command to use for solving the request
 */
public class View {
    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);
    MessageManager manager;
    Repository repository = Repository.getInstance();

    /**
     * language choice for all app. User have choice of 3 languages. If user try something wrong
     * language sets on english
     */
    public void languageChoice() {
        System.out.println("Choose language\n" +
                "-----------------------------\n" +
                "1.English 2.Russian 3.Belarus\n" +
                "-----------------------------");
        switch (scanner.next()) {
            case "1":
                manager = MessageManager.En;
                break;
            case "2":
                manager = MessageManager.Ru;
                break;
            case "3":
                manager = MessageManager.By;
                break;
            default:
                manager = MessageManager.En;
                System.out.println("There is no result like that. That's why language sets on english. Good luck");
        }
        System.out.println(manager.getString("congrats"));
    }

    /**
     * menu is main menu which user sees most time of this app and choose what he wants to do
     * main menu use another menus for different commands if controller need more data or information
     */
    public void menu() {
        languageChoice();
        controller.executeTask("INI " );
        boolean flag = true;
        String choice;
        while (flag) {
            System.out.println(manager.getString("mainChoise"));
            choice = scanner.next().toUpperCase();
            switch (choice) {
                case "1":
                    controller.executeTask("SHOW_LIST ");
                    break;
                case "2":
                    System.out.println(manager.getString("name"));
                    String clientInfo = "CREATE_CLIENT " + scanner.next();
                    System.out.println(manager.getString("passport"));
                    clientInfo += " " + scanner.next();
                    System.out.println(manager.getString("billNum"));
                    clientInfo += " " + scanner.next();
                    System.out.println(manager.getString("money"));
                    scanner.nextLine();
                    clientInfo += " " + scanner.nextLine();
                    clientInfo += " ";
                    if (controller.executeTask(clientInfo)) {
                        System.out.println(manager.getString("clientAdd"));
                    } else {
                        System.out.println(manager.getString("cantAdd"));
                    }
                    break;
                case "3":
                    sumChoice();
                    break;
                case "4":
                    System.out.println(manager.getString("passport"));
                    if (controller.executeTask("CLIENT_SUM " + scanner.next().toUpperCase() + " ")) {
                        System.out.println(manager.getString("clientSum") + repository.getResult());
                    } else {
                        System.out.println(manager.getString("noClient"));
                    }
                    break;
                case "5":
                    System.out.println(manager.getString("billID"));
                    if(controller.executeTask("SEARCH " + scanner.next() + " " )){
                        System.out.println(repository.getBill());
                        billMenu();
                    }else{
                        System.out.println(manager.getString("noBill"));
                    }
                    repository.setBill(null);
                    break;
                case "6":
                    sortChoice();
                    break;
                case "EXIT":
                    flag = false;
                    break;
                default:
                    System.out.println(manager.getString("wrong"));
            }
        }
    }

    /**
     * sumChoice is the menu which clarifies which type of sum user need (sum all bills, with negative values,
     * with positive values), if user choose exit and wrong command programme will back user to main menu
     */
    public void sumChoice() {
        System.out.println((manager.getString("sumChoice")));
        boolean result;
        String choice = scanner.next();
        switch (choice) {
            case "1":
                result = controller.executeTask("BILL_SUM 1 " );
                break;
            case "2":
                result = controller.executeTask("BILL_SUM 2 " );
                break;
            case "3":
                result = controller.executeTask("BILL_SUM 3 " );
                break;
            case "EXIT":
                return;
            default:
                System.out.println(manager.getString("wrong"));
                return;
        }
        if(result){
            System.out.println(manager.getString("sum")+repository.getResult());
        }else{
            System.out.println(manager.getString(manager.getString("incorrectData")));
        }
    }

    /**
     * sortChoice is the menu where user choose which type of bill sort user wants
     * 1-bilNum sort, 2-sum on bill sort if user choose exit and wrong command programme will back user to main menu
     */
    public void sortChoice() {
        System.out.println(manager.getString("sortChoice"));
        String choice = scanner.next();
        boolean result;
        switch (choice) {
            case "1":
                result = controller.executeTask("SORT 1");
                break;
            case "2":
                result = controller.executeTask("SORT 2");
                break;
            case "EXIT":
                return;
            default:
                System.out.println(manager.getString("wrong"));
                return;
        }
        if(!result){
            System.out.println(manager.getString(manager.getString("incorrectData")));
        }
    }

    /**
     * billMenu is a menu where user choose what he wants to do with his bill
     * 1-change sum on bil 2-block/unblock bill 3-exit
     */
    public void billMenu(){
        boolean flag=true;
        while(flag){
            System.out.println(manager.getString("billMenu"));
            switch (scanner.next().toUpperCase()){
                case "1":
                    double change= scanner.nextDouble();
                    repository.getBill().setMoney(change);
                    break;
                case "2":
                    if (repository.getBill().isBlocked()) {
                        repository.getBill().unblock();
                        System.out.println(manager.getString("unblocked"));
                    } else {
                        repository.getBill().block();
                        System.out.println(manager.getString("blocked"));
                    }
                    break;
                case "3":
                    flag=false;
                    break;
                default:
                    System.out.println(manager.getString("wrong"));
            }
        }
    }

}
