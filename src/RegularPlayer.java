import java.util.ArrayList;

public class RegularPlayer extends BothPlayer{
    public RegularPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score, level);
    }

    @Override
    public Card playCard() {
        return null;
    }
}
