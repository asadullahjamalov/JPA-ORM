package menu;

import java.util.Scanner;

public class MenuUtil {
    public static String menuDetail(String info){
        System.out.println("-------------"+info+" Menu-------------");
        System.out.println("Choose one of the choices in the "+info+" Menu: ");
        System.out.println("1)Add new "+info);
        System.out.println("2)Show all "+info);
        System.out.println("3)Edit "+info);
        System.out.println("4)Remove "+info);
        System.out.println("5)Search "+info+" based on name (/name part)");
        System.out.println("6)Return to Main Menu");
        System.out.println("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        return choice;
    }

    public static void wrongChoiceMessage(){
        System.out.println("There is not such choice," +
                " please enter between 1-6");
    }
}
