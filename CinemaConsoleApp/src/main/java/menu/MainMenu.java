package menu;

import util.JPAUtil;

import java.util.Scanner;

import static menu.GenreMenu.showGenreMenu;
import static menu.MovieMenu.showMovieMenu;
import static menu.PersonMenu.showPersonMenu;
import static menu.ProfessionMenu.showProfessionMenu;

public class MainMenu {

    public static void showMainMenu() {

        OUT:
        while (true) {
            System.out.println("-------------Main Menu-------------");
            System.out.println("Choose one of the choices in the Main Menu: ");
            System.out.println("1)Movie Menu");
            System.out.println("2)Genre Menu");
            System.out.println("3)Person Menu");
            System.out.println("4)Profession Menu");
            System.out.println("5)Exit");
            System.out.println("Enter your choice: ");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            if (choice.matches("[1-5]")) {
                switch (choice) {
                    case "1":
                        showMovieMenu();
                        break;
                    case "2":
                        showGenreMenu();
                        break;
                    case "3":
                        showPersonMenu();
                        break;
                    case "4":
                        showProfessionMenu();
                        break;
                    case "5":
                        JPAUtil.shutdown();
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