package app;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import app.model.Critter;

public class MainWindow {

    @FXML
    private Pane pane;

    @FXML
    public void initialize() {
    }


    /**
     * Gets the ImageView for a specified Critter object
     * @param critter
     * @return ImageView representing the Critter
     */
    private ImageView getCritterImageView(Critter critter) {
         Optional<Node> optional = pane.getChildren().stream()
                .filter(c -> c.getUserData() == critter).findFirst();
        if (optional.isPresent()) return (ImageView) optional.get();
        else                      return null;
    }
}
