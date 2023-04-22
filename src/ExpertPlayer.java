import java.util.ArrayList;

public class ExpertPlayer extends BothPlayer{
    public ExpertPlayer(String name, ArrayList<Card> hand, int score) {
        super(name, hand, score);
    }

    @Override
    public Card playCard() {
        return null;
    }
}
