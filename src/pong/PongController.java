package pong;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class PongController {
    private PongModel model;
    private PongView view;

    public PongController(PongModel model, PongView view, Scene scene) {
        this.model = model;
        this.view = view;
        initializeControls(scene);
    }

        private void initializeControls(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP && model.getPaddleY() > 0) {
                model.updatePaddlePosition(-PongConfig.PADDLE_SPEED);
            } else if (event.getCode() == KeyCode.DOWN && model.getPaddleY() < PongConfig.HEIGHT - PongConfig.PADDLE_HEIGHT) {
                model.updatePaddlePosition(PongConfig.PADDLE_SPEED);
            }
        });
    }

    public void updateGame() {
        model.updateBallPosition();
        view.updatePaddlePosition(model.getPaddleY());
        view.updateBallPosition(model.getBallX(), model.getBallY());
    }
}
