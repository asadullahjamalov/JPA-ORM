package menu;

import static menu.MenuUtil.*;
import static util.PersonUtil.*;

public class PersonMenu {
    public static void showPersonMenu()  {
        OUT:
        while (true) {
            String choice = menuDetail("Person");
            if (choice.matches("[1-6]")) {
                switch (choice) {
                    case "1":
                        addPerson();
                        break;
                    case "2":
                        showAllPeople();
                        break;
                    case "3":
                        updatePerson();
                        break;
                    case "4":
                        removePerson();
                        break;
                    case "5":
                        searchPersonByName();
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
