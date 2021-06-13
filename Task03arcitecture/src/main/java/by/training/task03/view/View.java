package by.training.task03.view;

import by.training.task03.controller.Controller;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class View {
    Controller controller= new Controller();
    Scanner scanner= new Scanner(System.in);
    String request;
    int initialized=0;
    Locale locale;
    ResourceBundle rb;

    public void languageChoice(){
        System.out.println("Choose language\n" +
                "-----------------------------\n"+
                "1.English 2.Russian 3.Belarus\n"+
                "-----------------------------");
        switch (scanner.next()){
            case "1":
                locale=new Locale("eng","BR");
                break;
            case "2":
                locale = new Locale("ru","RU");
                break;
            case "3":
                locale = new Locale("be","BY");
                break;
            default:
                System.out.println("There is no result like that. That's why language sets on english. Good luck");
        }
        rb=ResourceBundle.getBundle("property.text",locale);
        String congratulation=rb.getString("cograts");
        System.out.println(congratulation);
    }

    public void menu(){
        languageChoice();
        String arrayOrMatrix=rb.getString("choice1");
        boolean flag=true;
        while (flag==true){
            System.out.println(arrayOrMatrix);
            switch (scanner.next().trim().toUpperCase()){
                case "1":
                    arrayMenu();
                    break;
                case "2":
                    if(initialized==0){
                        matrixFillMenu();
                    }
                    matrixMenu();
                    break;
                case "3":
                    flag=false;
                    break;
                default:
                    System.out.println(controller.executeTask("WRONG_REQUEST "));
            }
        }
    }

    public void arrayMenu(){
        String arrayMenu= rb.getString("sortChoice");
        controller.executeTask("ARRAY_INI ");
        System.out.println(arrayMenu);
        switch (scanner.next().trim().toUpperCase()){
            case "1":
                request="BUBBLE_SORT ";
                break;
            case "2":
                request="SHAKE_SORT ";
                break;
            case "3":
                request="CHOICE_SORT ";
                break;
            case "4":
                request="INSERTION_SORT ";
                break;
            case "5":
                request="SHELL_SORT ";
                break;
            case "6":
                request ="MERGE_SORT ";
                break;
            case "7":
                return;
            default:
                request="WRONG_REQUEST ";
        }
        System.out.println(controller.executeTask(request));
    }

    public void matrixMenu(){
        String matrixMenu=rb.getString("matrixAction");
        if(initialized==1){
            System.out.println(rb.getString("matrixAction1"));
            switch (scanner.next().trim().toUpperCase()){
                case "1":
                    request = "TRANSPOSITION ";
                    break;
                case "2":
                    request = "NUM_MULTIPLICATION ";
                    String numInput = rb.getString("numInput");
                    System.out.println(numInput);
                    request += scanner.next()+" ";
                    break;
                case "3":
                    request = "CLEAR 1";
                    initialized = 0;
                    matrixFillMenu();
                    break;
                    case "4":
                    return;
                default:
                    request = "WRONG_REQUEST ";
            }
            System.out.println(controller.executeTask(request));
        }else if(initialized==2){
            System.out.println(rb.getString("matrixAction"));
            switch (scanner.next().trim().toUpperCase()){
                case "1":
                    request = "SUM ";
                    break;
                case "2":
                    request = "SUBTRACTION ";
                    break;
                case "3":
                    request = "MULTIPLICATION ";
                    break;
                case "4":
                    request = "CLEAR 2";
                    initialized = 0;
                    matrixFillMenu();
                    break;
                case "5":
                    return;
                default:
                    request = "WRONG_REQUEST ";
            }
            System.out.println(controller.executeTask(request));
        }

    }
    
    public void matrixFillMenu(){
        String method=rb.getString("inputChoice");
        String matrixFillMenu= rb.getString("chice2");
        String data1=rb.getString("hand");
        String data2=rb.getString("random");
        System.out.println(matrixFillMenu);
        switch (scanner.next().trim().toUpperCase()){
            case "1":
                System.out.println(method);
                String request=scanner.next();
                if(request.equals("1")){
                    request="HAND_INI ";
                    System.out.println(data1);
                    request+=scanner.next()+" ";
                    System.out.println(rb.getString("hand1") );
                    request+=scanner.next()+" 0";
                }else if(request.equals("2")){
                    request="RANDOM_INI ";
                    System.out.println(data2);
                    for(int j=0;j<4;j++)
                        request+=scanner.next()+" 0";
                }else{
                    request="WRONG_REQUEST ";
                }
                String response=controller.executeTask(request);
                if(response.equals("matrix added")){
                    System.out.println(response);
                    initialized=1;
                }else{
                    System.out.println(response);
                }

                break;
            case "2":
                for(int i=0;i<2;i++) {
                    System.out.println(method);
                    request=scanner.next();
                    if(request.equals("1")){
                        request="HAND_INI ";
                        System.out.println(data1);
                        request+=scanner.next()+" ";
                        System.out.println(data1=rb.getString("hand1") );
                        request+=scanner.next()+" "+i;
                    }else if(request.equals("2")){
                        request="RANDOM_INI ";
                        System.out.println(data2);
                        for(int j=0;j<4;j++) {
                            request += scanner.next() + " ";
                        }
                        request+=i;
                    }else{
                        request="WRONG_REQUEST ";
                    }
                    response=controller.executeTask(request);
                    if(response.equals("matrix added")){
                        System.out.println(response);
                        initialized=2;
                    }else {
                        initialized=0;
                        System.out.println(response);
                        break;
                    }
                }
                break;
        }
    }

}
