import Threads.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button StartButton = new Button("Start");
        StartButton.setLayoutX(60);
        StartButton.setLayoutY(10);
        Button PauseButton = new Button("Pause");
        PauseButton.setLayoutX(60);
        PauseButton.setLayoutY(40);
        Button StopButton = new Button("Stop");
        StopButton.setStyle("-fx-background-color: red;");
        StopButton.setLayoutX(60);
        StopButton.setLayoutY(70);
        ProgressBar pb = new ProgressBar(0);
        pb.setLayoutX(60);
        pb.setLayoutY(100);

        Thread1 T1 = new Thread1(pb);

        StartButton.setOnAction(value ->  {
            T1.mystart();
        });

        PauseButton.setOnAction(value ->  {
            if(T1.t.getState() != Thread.State.NEW) {
                if (!T1.suspendFlag) {
                    PauseButton.setText("Resume");
                    T1.mysuspend();
                }
                else {
                    PauseButton.setText("Pause");
                    T1.myresume();
                }
            }
        });
        StopButton.setOnAction(value ->  {
            T1.myend();
        });

        var root = new Pane();
        root.setStyle("-fx-background-color: cornsilk;");
        root.getChildren().addAll(StartButton, PauseButton, StopButton, pb);
        Scene scene = new Scene(root, 512, 512);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}