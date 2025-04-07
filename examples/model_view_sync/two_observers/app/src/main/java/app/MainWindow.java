package app;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class MainWindow implements Observer {


    public void initialize() {
        CommandPrompt commandPrompt = new CommandPrompt();
        Model.getInstance().addObserver(commandPrompt);
        Model.getInstance().addObserver(this);
    }

    @FXML
    void onTriggerMove(ActionEvent event) {
        Model.getInstance().generateMove();
    }

    @Override
    public void hasMoved(int moveValue) {
        displayAlert("The move was " + moveValue);
    }
    
    @Override
    public void gameOver() {
        displayAlert("GAMEOVER");
    }

    private void displayAlert(String text) {
        var alert = new Alert(AlertType.INFORMATION, text);
        alert.setHeaderText(null);
        alert.show();
    }
}