package memory;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MemoryGame extends Application {
    private static List<Card> selectedCards = new ArrayList<>();
    private static int moveCount = 0;
    private static Label moveInfoLabel;
    private static int matchedCards = 0;
    private static CardStrategy cardStrategy;
    private static boolean isFlipping = false;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        Button changeCardsButton = new Button("Change Configuration");
        changeCardsButton.setOnAction(event -> changeCardsConfiguration(grid));

        grid.add(changeCardsButton, 0, 0, 1, 1);
        cardStrategy = new EightCardStrategy(grid);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Memory Game");
        primaryStage.show();

        displayMoveInfo(grid);

        cardStrategy.displayCards();
    }

    private void displayMoveInfo(GridPane grid) {
        moveInfoLabel = new Label("Moves: " + moveCount);
        grid.add(moveInfoLabel, 2, 0, 8, 1);
    }

    public static void handleCardClick(Card card) {
        if (!isFlipping && selectedCards.size() < 2) {
            card.changeState();
            selectedCards.add(card);

            if (selectedCards.size() == 2) {
                compareSelectedCards();
            }
        }
    }

    private static void compareSelectedCards() {
        isFlipping = true;
        moveCount++;
        updateMoveInfoLabel();
        if (selectedCards.get(0).isMatched(selectedCards.get(1))) {
            selectedCards.clear();
            matchedCards++;
            checkForWin();
            isFlipping = false;
        } else {
            resetSelectedCardsAfterDelay();
        }
    }

    private static void resetSelectedCardsAfterDelay() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Card card : selectedCards) {
                card.changeState();
            }

            selectedCards.clear();
            isFlipping = false;
        }).start();
    }

    private static void updateMoveInfoLabel() {
        Platform.runLater(() -> {
            moveInfoLabel.setText("Moves: " + moveCount);
        });
    }

    private static void checkForWin() {
        if (matchedCards == cardStrategy.getCardCount() / 2) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulations!");
                alert.setHeaderText(null);
                alert.setContentText("You won the game in " + moveCount + " moves!");
                alert.showAndWait();
            });
        }
    }

    private void changeCardsConfiguration(GridPane grid) {
        grid.getChildren().removeIf(node -> node instanceof Card);
        matchedCards = 0;
        moveCount = 0;
        updateMoveInfoLabel();

        if (cardStrategy instanceof EightCardStrategy) {
            cardStrategy = new SixteenCardStrategy(grid);
        } else if (cardStrategy instanceof SixteenCardStrategy) {
            cardStrategy = new TwentyFourCardStrategy(grid);
        } else {
            cardStrategy = new EightCardStrategy(grid);
        }

        cardStrategy.displayCards();
    }

    static List<Image> getImageList(int count) {
        List<Image> images = new ArrayList<>();
        for (int i = 1; i <= count / 2; i++) {
            images.add(new Image("file:src/resources/MemoryImages/" + i + ".png"));
        }
        return images;
    }
}