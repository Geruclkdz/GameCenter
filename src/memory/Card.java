package memory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Card extends StackPane {
    private final int id;
    private final Image frontImage;
    private final Image backImage;
    private final ImageView imageView;

    private CardState currentState;

    public Card(int id, Image frontImage, Image backImage) {
        this.id = id;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.imageView = new ImageView(backImage);
        this.currentState = new HiddenState();

        imageView.setFitWidth(90);
        imageView.setFitHeight(90);
        setPrefSize(100, 100);
        getChildren().add(imageView);

        setOnMouseClicked(event -> handleClick());
    }

    public void setState(CardState state) {
        this.currentState = state;
    }

    private void handleClick() {
        MemoryGame.handleCardClick(this);
    }

    public void changeState() {
        currentState.handleCardClick(this);
    }

    public boolean isMatched(Card otherCard) {
        return this.id == otherCard.id;
    }

    public void flip() {
        if (imageView.getImage() == frontImage) {
            imageView.setImage(backImage);
        } else {
            imageView.setImage(frontImage);
        }
    }
}
