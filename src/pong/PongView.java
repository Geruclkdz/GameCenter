package pong;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PongView {
    private Pane root;
    private Rectangle paddle;
    private Circle ball;

    public PongView(Pane root) {
        this.root = root;
        initializeView();
    }

    private void initializeView() {
        paddle = new Rectangle(PongConfig.PADDLE_WIDTH, PongConfig.PADDLE_HEIGHT, Color.BLUE);
        ball = new Circle(PongConfig.BALL_RADIUS, Color.RED);

        paddle.setTranslateX(PongConfig.PADDLE_INITIAL_X);
        paddle.setTranslateY(PongConfig.INITIAL_PADDLE_Y);

        ball.setTranslateX(PongConfig.INITIAL_BALL_X);
        ball.setTranslateY(PongConfig.INITIAL_BALL_Y);

        root.getChildren().addAll(paddle, ball);
    }

    public void updatePaddlePosition(double paddleY) {
        paddle.setTranslateY(paddleY);
    }

    public void updateBallPosition(double ballX, double ballY) {
        ball.setTranslateX(ballX);
        ball.setTranslateY(ballY);
    }
}
