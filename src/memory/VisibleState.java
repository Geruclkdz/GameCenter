package memory;

public class VisibleState implements CardState {

    @Override
    public void handleCardClick(Card card) {
        card.flip();
        card.setState(new HiddenState());
    }
}
