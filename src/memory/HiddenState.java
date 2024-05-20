package memory;

public class HiddenState implements CardState {

    @Override
    public void handleCardClick(Card card) {
        card.flip();
        card.setState(new VisibleState());
    }
}
