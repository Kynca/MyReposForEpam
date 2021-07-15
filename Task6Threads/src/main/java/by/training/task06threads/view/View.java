package by.training.task06threads.view;

import by.training.task06threads.bean.MatrixStorage;
import by.training.task06threads.controller.Controller;

import java.util.Scanner;

public class View {
    Controller controller = new Controller();
    Scanner scanner = new Scanner(System.in);
    MatrixStorage storage = MatrixStorage.getInstance();

    public void menu() {
        boolean flag = true;
        String request;
        while (flag) {
            request = "INI ";
            System.out.println("");
            request += scanner.next() + " ";
            System.out.println("");
            request += scanner.next() + " ";
            controller.executeTask(request);
            request = "THREAD ";
            System.out.println("");
            request += scanner.next() + " ";
            System.out.println("");
            request += scanner.next() + " ";
            controller.executeTask(request);
            if (storage.getChangedArray() != null && storage.getNumberOfThreads() == 0) {
                flag = false;
            }
        }

        while (flag) {
            System.out.println(" menu");
            switch (scanner.next()) {
                case "1":
                    controller.executeTask("LOCK ");
                    break;
                case "2":
                    controller.executeTask("POOL ");
                    break;
                case "3":
                    controller.executeTask("BARRIER ");
                    break;
                case "4":
                    controller.executeTask("SEMAPHORE ");
                    break;
                case "5":
                    flag = false;
                    break;
            }
            double[][] end = storage.getChangedArray();
            for (int i = 0; i < end.length; i++) {
                for (int j = 0; j < end.length; j++) {
                    System.out.print(end[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println(storage.getChanges());
            storage.reset();
        }
    }
}
