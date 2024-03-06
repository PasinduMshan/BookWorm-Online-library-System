package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeFormController implements Initializable {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private AnchorPane rootNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event ->{
                    double progress = progressBar.getProgress() + 0.01;
                    progressBar.setProgress(progress);
                }) ,
                new KeyFrame(Duration.millis(30))
        );

        timeline.setCycleCount(100);
        timeline.play();

        timeline.setOnFinished(action ->{

        });
    }
}
