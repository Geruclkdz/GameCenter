package pong;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class PongModel {
    private double paddleY;
    private double ballX;
    private double ballY;
    private double ballSpeedX;
    private double ballSpeedY;

    public PongModel() {
        paddleY = PongConfig.INITIAL_PADDLE_Y;
        ballX = PongConfig.INITIAL_BALL_X;
        ballY = PongConfig.INITIAL_BALL_Y;
        ballSpeedX = PongConfig.BALL_SPEED_X;
        ballSpeedY = PongConfig.BALL_SPEED_Y;
    }

    public double getPaddleY() {
        return paddleY;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }

    public double getBallSpeedX() {
        return ballSpeedX;
    }

    public double getBallSpeedY() {
        return ballSpeedY;
    }

    public void updatePaddlePosition(double deltaY) {
        paddleY += deltaY;
    }

    public void updateBallPosition() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        if(ballX - PongConfig.BALL_RADIUS <= 0){
            stopGameAndShowAlert("THE END! YOU LOST");
        }


        if (ballX + PongConfig.BALL_RADIUS >= PongConfig.WIDTH) {
            ballSpeedX = -ballSpeedX;
        }

        if (ballY + PongConfig.BALL_RADIUS >= paddleY && ballY - PongConfig.BALL_RADIUS <= paddleY + PongConfig.PADDLE_HEIGHT) {
            if (ballX + PongConfig.BALL_RADIUS >= PongConfig.PADDLE_INITIAL_X &&
                    ballX - PongConfig.BALL_RADIUS <= PongConfig.PADDLE_INITIAL_X + PongConfig.PADDLE_WIDTH) {
                // Ball hits the paddle
                ballSpeedX = -ballSpeedX; // Reverse the ball's horizontal direction
            }
        }

        // Ball hits the top or bottom of the window
        if (ballY - PongConfig.BALL_RADIUS <= 0 || ballY + PongConfig.BALL_RADIUS >= PongConfig.HEIGHT) {
            ballSpeedY = -ballSpeedY; // Reverse the ball's vertical direction
        }

    }
    private void stopGameAndShowAlert(String message) {
        ballSpeedX = 0; // Stop the ball
        ballSpeedY = 0; // Stop the ball
        ballX = 20;

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.showAndWait();

            System.exit(0); // Terminate the application
        });
    }
}
