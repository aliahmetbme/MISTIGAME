import java.util.ArrayList;
import java.util.Random;

public abstract class BothPlayer extends Player{

    public BothPlayer(String name, ArrayList<Card> hand, int score) {
        super(name, hand, score);
    }

    public abstract Card playCard() ;
}
