package by.training.task05.view;

import by.training.task05.bean.Cube;
import by.training.task05.bean.CubeRegistrar;
import by.training.task05.bean.CubeStorage;
import by.training.task05.bean.MessageManager;
import by.training.task05.controller.Controller;
import org.apache.logging.log4j.core.util.JsonUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {
    CubeStorage storage = CubeStorage.getInstance();
    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);
    MessageManager manager;

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

    public void menu() {
        languageChoice();
        controller.executeTask("Fill data/cubeData.txt");
        showStorage();
        boolean flag = true;
        String request;
        while (flag) {
            System.out.println(manager.getString("cubeMenu"));
            switch (scanner.next()) {
                case "1":
                    scanner.nextLine();
                    request = "ADD ";
                    for (int i = 0; i < 8; i++) {
                        System.out.println(manager.getString("point") + " " + (i + 1));
                        request += scanner.nextLine() + " ";
                    }
                    System.out.println(manager.getString("nameInput"));
                    request += scanner.next();
                    if (controller.executeTask(request) != null) {
                        System.out.println(manager.getString("add"));
                    } else {
                        System.out.println(manager.getString("notAdd"));
                    }
                    break;
                case "2":
                    System.out.println(manager.getString("id"));
                    if (controller.executeTask("REMOVE " + scanner.next()) != null) {
                        System.out.println(manager.getString("deleted"));
                    } else {
                        System.out.println(manager.getString("notDeleted"));
                    }
                    break;
                case "3":
                    findMenu();
                    break;
                case "4":
                    sortMenu();
                    break;
                case "5":
                    showStorage();
                    break;
                case "6":
                    flag = false;
                    break;
                default:
                    System.out.println(manager.getString("wrong"));
            }
        }
    }

    private void findMenu() {
        System.out.println(manager.getString("findMenu"));
        String request;
        switch (scanner.next()) {
            case "1":
                System.out.println(manager.getString("id"));
                request = "FIND id " + scanner.next();
                break;
            case "2":
                System.out.println(manager.getString("nameInput"));
                request = "FIND Name " + scanner.next();
                break;
            case "3":
                System.out.println(manager.getString("sizeInput"));
                request = "FIND Size " + scanner.next();
                break;
            case "4":
                request = "FIND Plane";
                break;
            case "5":
                System.out.println(manager.getString("sizeInput"));
                System.out.println(manager.getString("sizeInput"));
                request = "FIND Size " + scanner.next() + " " + scanner.next();
                break;
            default:
                System.out.println(manager.getString("wrong"));
                return;
        }
        List list = controller.executeTask(request);
        if (list != null || list.size() > 0) {
            showList(list);
        } else {
            System.out.println(manager.getString("cubeFound"));
        }
        if (list.size() == 1) {
            System.out.println(manager.getString("updateChoice"));
            switch (scanner.next()) {
                case "1":
                    updateMenu();
                    break;
                case "2":
                    return;
                default:
                    System.out.println(manager.getString("wrong"));
                    return;
            }
        }
    }

    private void updateMenu() {
        String request = "Update ";
        System.out.println(manager.getString("indexInput"));
        request += scanner.next() + " ";
        System.out.println(manager.getString("inputPoint"));
        request += scanner.next();
    }

    private void sortMenu() {
        System.out.println(manager.getString("sortChoice"));
        String request = "SORT ";
        switch (scanner.next()) {
            case "1":
                request += "ID";
                break;
            case "2":
                request += "NAME";
                break;
            case "3":
                request += "X";
                break;
            case "4":
                request += "Y";
                break;
            case "5":
                request += "Z";
                break;
            default:
                System.out.println(manager.getString("wrong"));
                return;
        }
        showList(controller.executeTask(request));
    }

    private void showStorage() {
        char[] pointName = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        for (Map.Entry entry : storage.getCubeStorage().entrySet()) {
            Cube cube = (Cube) entry.getKey();
            CubeRegistrar registrar = (CubeRegistrar) entry.getValue();
            System.out.println("id = " + cube.getId() + " " + manager.getString("name") + cube.getName());
            System.out.println();
            Cube.Point[] points = cube.getPoints();
            for (int i = 0; i < pointName.length; i++) {
                System.out.print(pointName[i] + " = " + points[i].getX() + " " + points[i].getY() + " " + points[i].getZ());
                System.out.println();
            }
            String onPlane;
            if (registrar.isOnCoordinatePlane()) {
                onPlane = manager.getString("onPlane");
            } else {
                onPlane = manager.getString("notOnPlane");
            }
            System.out.println(manager.getString("size") + registrar.getSize() + " " + manager.getString("area")
                    + registrar.getCubeArea() + " " + manager.getString("volume") + registrar.getCubeVolume() + " "
                    + onPlane);
        }
    }

    private void showList(List<Cube> list) {

        char[] pointName = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        if (list == null || list.size() < 1) {
            System.out.println(manager.getString("wrongList"));
            return;
        }

        for (Cube cube : list) {
            System.out.println("id = " + cube.getId() + " " + manager.getString("name") + cube.getName());
            System.out.println();
            CubeRegistrar registrar = (CubeRegistrar) cube.getObservers().get(0);
            Cube.Point[] points = cube.getPoints();
            for (int i = 0; i < pointName.length; i++) {
                System.out.print(pointName[i] + " = " + points[i].getX() + " " + points[i].getY() + " " + points[i].getZ());
                System.out.println();
            }
            String onPlane;
            if (registrar.isOnCoordinatePlane()) {
                onPlane = manager.getString("onPlane");
            } else {
                onPlane = manager.getString("notOnPlane");
            }
            System.out.println(manager.getString("size") + registrar.getSize() + " " + manager.getString("area")
                    + registrar.getCubeArea() + " " + manager.getString("volume") + registrar.getCubeVolume() + " "
                    + onPlane);
        }
    }

}
