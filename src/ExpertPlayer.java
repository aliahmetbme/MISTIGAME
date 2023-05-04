import java.util.ArrayList;

public class ExpertPlayer extends BothPlayer{
    public ExpertPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score, level);
    }

    @Override
    public Card playCard() {
        return null;
    }
}
