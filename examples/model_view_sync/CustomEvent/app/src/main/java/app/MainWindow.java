package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;



public class MainWindow {

    @FXML Label labelResults;
    @FXML Model model;

    @FXML
    public void initialize() {
        model = new Model();
        model.setOnGotTen(i -> labelResults.setText(labelResults.getText() + i + ": Rolled a Ten\n"));
    }

    @FXML
    void onStartClicked(ActionEvent event) {
        labelResults.setText("");
        model.playGame();
    }
}
