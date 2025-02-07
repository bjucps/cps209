package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class MainWindow {


    @FXML
    void onStartClicked(ActionEvent event) {
        
        displayAlert("Difficulty is ?");
    }

    private void displayAlert(String text) {
        var alert = new Alert(AlertType.INFORMATION, text);
        alert.setHeaderText(null);
        alert.show();
    }
}