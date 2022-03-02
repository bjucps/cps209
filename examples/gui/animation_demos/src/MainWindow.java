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

    @FXML ImageView imgView;

    @FXML Label lblTime;

    Timeline timeline;

    @FXML Button btnStart;


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
            try { Thread.sleep(200); } catch (Exception e) { }
        }
    }

    int count = 0;

    @FXML
    void onStartClicked() {
        btnStart.setDisable(true);
        timeline = new Timeline(new KeyFrame(Duration.millis(500), this::updateMan));
        timeline.setCycleCount(50);
        timeline.play();
    }

    void updateMan(ActionEvent e) { 
        imgView.setX(imgView.getX() + 4);
        count++;
        if (count == 50)
            btnStart.setDisable(false);
    }

    @FXML
    void onStopClicked() {
        timeline.stop();
        btnStart.setDisable(false);
    }

}
