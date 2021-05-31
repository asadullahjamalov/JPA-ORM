package menu;

import static menu.MenuUtil.*;
import static util.ProfessionUtil.*;

public class ProfessionMenu {
    public static void showProfessionMenu() {
        OUT:
        while (true) {
            String choice = menuDetail("Profession");
            if (choice.matches("[1-6]")) {
                switch (choice) {
                    case "1":
                        addProfession();
                        break;
                    case "2":
                        showAllProfessions();
                        break;
                    case "3":
                        updateProfession();
                        break;
                    case "4":
                        removeProfession();
                        break;
                    case "5":
                        searchProfessionByName();
                        break;
                    case "6":
                        break OUT;
                }
            } else {
                wrongChoiceMessage();
                continue;
            }
        }
    }
}

