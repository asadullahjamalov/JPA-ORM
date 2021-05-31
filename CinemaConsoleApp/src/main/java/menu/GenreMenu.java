package menu;

import static menu.MenuUtil.*;
import static util.GenreUtil.*;

public class GenreMenu {
    public static void showGenreMenu(){
        OUT:
        while (true) {
            String choice = menuDetail("Genre");
            if (choice.matches("[1-6]")) {
                switch (choice) {
                    case "1":
                        addGenre();
                        break;
                    case "2":
                        showAllGenres();
                        break;
                    case "3":
                        updateGenre();
                        break;
                    case "4":
                        removeGenre();
                        break;
                    case "5":
                        searchGenreByName();
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
