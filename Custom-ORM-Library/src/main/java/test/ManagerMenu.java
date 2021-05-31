package test;


import java.util.Scanner;

import static test.ManagerUtil.*;

public class ManagerMenu {
    public static void showManagerMenu() {
        OUT:
        while (true) {
            System.out.println("-------------Manager Menu-------------");
            System.out.println("Choose one of the choices in the Manager Menu: ");
            System.out.println("1)Add new Manager");
            System.out.println("2)Show all Manager");
            System.out.println("3)Edit Manager (not completed)");
            System.out.println("4)Remove Manager");
            System.out.println("5)Exit");
            System.out.println("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (choice.matches("[1-5]")) {
                switch (choice) {
                    case "1":
                        addManager();
                        break;
                    case "2":
                        showAllManagers();
                        break;
                    case "3":
//                        updateManager();
                        break;
                    case "4":
                        removeManager();
                        break;
                    case "5":
                        break OUT;

                }
            } else {
                System.out.println("There is not such choice," +
                        " please enter between 1-5");
                continue;
            }
        }
    }
}
