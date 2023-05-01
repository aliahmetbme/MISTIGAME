import java.util.ArrayList;

public class NovicePlayer extends BothPlayer{
    public NovicePlayer(String name, ArrayList<Card> hand, int score,String level) {
        super(name, hand, score, level);
    }

    @Override
    public Card playCard() {
        return null;
    }
}
