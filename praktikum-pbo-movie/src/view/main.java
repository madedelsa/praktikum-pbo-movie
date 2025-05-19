package view;
import controller.MovieController;

/**
 * @author Made Delsa
 */
public class main {
public static void main(String[] args) {
        MovieView view = new MovieView();
        MovieController controller = new MovieController(view);
    }    
}
