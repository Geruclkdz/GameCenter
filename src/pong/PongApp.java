package pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PongApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, PongConfig.WIDTH, PongConfig.HEIGHT);

        PongModel model = new PongModel();
        PongView view = new PongView(root);
        PongController controller = new PongController(model, view, scene);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(PongConfig.FRAME_INTERVAL), event -> {
            controller.updateGame();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
