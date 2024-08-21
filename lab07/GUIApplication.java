
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Example comment.
<<<<<<< HEAD
// A boring substandard comment.
=======
// way better comment.
>>>>>>> 8df42776fbef9bf87409c89121ebf78a3cf5c772
public class GUIApplication extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        var loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        var scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe"); // Title of main window
        stage.show();
    }

}
