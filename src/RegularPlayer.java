import java.util.ArrayList;

public class RegularPlayer extends BothPlayer{
    public RegularPlayer(String name, ArrayList<Card> hand, int score) {
        super(name, hand, score);
    }

    @Override
    public Card playCard() {
        return null;
    }
}
