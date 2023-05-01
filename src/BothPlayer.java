import java.util.ArrayList;
import java.util.Random;

public abstract class BothPlayer extends Player{
    public BothPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score, level);
    }

    public abstract Card playCard() ;
}
