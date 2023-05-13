import java.util.ArrayList;
import java.util.Random;

public abstract class BothPlayer extends Player{
    static Card topcard;

    public BothPlayer(String name, ArrayList<Card> hand, int score, String level, ArrayList<Card> storedCards) {
        super(name, hand, score, level,storedCards);

    }
    public abstract Card playCard() ;
}
