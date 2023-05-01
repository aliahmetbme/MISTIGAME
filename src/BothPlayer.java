import java.util.ArrayList;
import java.util.Random;

public abstract class BothPlayer extends Player{
<<<<<<< HEAD
    public BothPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score, level);
=======

    public BothPlayer(String name, ArrayList<Card> hand, int score) {
        super(name, hand, score);
>>>>>>> origin/ayça
    }

    public abstract Card playCard() ;
}
