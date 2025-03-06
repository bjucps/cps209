package app;

import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MainWindow {

    @FXML
    ImageView imgView;

    @FXML
    Label lblTime;

    Timeline timeline;

    @FXML
    Button btnStart;

    private static final int NUM_MOVES = 100;
    private int count = 0;

    @FXML
    void initialize() {
        var keyFrame = new KeyFrame(Duration.millis(1000), this::updateClock);
        var clockTimeline = new Timeline(keyFrame);

        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
    }

    void updateClock(ActionEvent e) {
        lblTime.setText(new Date().toString());
    }

    @FXML
    void onBrokenStartClicked(ActionEvent event) {
        for (int i = 0; i < 20; ++i) {
            imgView.setLayoutX(imgView.getLayoutX() + 4);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
            }
        }
    }

    
    @FXML
    void onStartClicked() {
        btnStart.setDisable(true);
        timeline = new Timeline(new KeyFrame(Duration.millis(25), this::updateMan));
        timeline.setCycleCount(NUM_MOVES);
        timeline.play();
    }

    void updateMan(ActionEvent e) {
        imgView.setX(imgView.getX() + 1);
        count++;
        if (count == NUM_MOVES) {
            count = 0;
            btnStart.setDisable(false);
        }
    }

    @FXML
    void onStopClicked() {
        timeline.stop();
        btnStart.setDisable(false);
    }

}
