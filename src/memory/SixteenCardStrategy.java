package memory;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SixteenCardStrategy implements CardStrategy {
    private final GridPane grid;
    List<Card> cardList = new ArrayList<>();

    SixteenCardStrategy(GridPane grid) {
        this.grid = grid;
    }

    @Override
    public void displayCards() {
        List<Image> images = MemoryGame.getImageList(16);
        List<Card> cardList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Image frontImage = images.get(i);
            Image backImage = new Image("file:src/resources/MemoryImages/CardBack.png");

            Card card1 = new Card(i + 1, frontImage, backImage);
            Card card2 = new Card(i + 1, frontImage, backImage);
            cardList.add(card1);
            cardList.add(card2);

        }

        Collections.shuffle(cardList);

        int columnIndex = 0;
        int rowIndex = 1;

        for(Card card: cardList){
            grid.add(card, columnIndex, rowIndex);

            columnIndex++;
            if (columnIndex == 4) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    @Override
    public int getCardCount() {
        return 16;
    }
}