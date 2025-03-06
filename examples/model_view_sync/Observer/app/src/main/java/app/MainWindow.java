package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;

public class MainWindow implements Observer {

    @FXML
    Label labelResults;
    Model model;

    @FXML
    public void initialize() {
        model = new Model();
        model.setObserver(this);
    }

    @FXML
    void onStartClicked(ActionEvent event) {
        labelResults.setText("");
        model.playGame();
    }

    @Override
    public void gotTen(int i) {
        labelResults.setText(labelResults.getText() + i + ": Rolled a Ten.\n");
    }

    @Override
    public void finished(int i) {
        labelResults.setText(labelResults.getText() + "Completed " + i + " rolls!\n");
        AudioClip clip = new AudioClip(getClass().getResource("sound.wav").toString());
        clip.play();
    }
}
