package menu;

import static menu.MenuUtil.*;
import static util.MovieUtil.*;

public class MovieMenu {
    public static void showMovieMenu() {
        OUT:
        while (true) {
            String choice = menuDetail("Movie");
            if (choice.matches("[1-6]")) {
                switch (choice) {
                    case "1":
                        addMovie();
                        break;
                    case "2":
                        showAllMovies();
                        break;
                    case "3":
                        updateMovie();
                        break;
                    case "4":
                        removeMovie();
                        break;
                    case "5":
                        searchMovieByName();
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


