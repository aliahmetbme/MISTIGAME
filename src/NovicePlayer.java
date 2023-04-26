import java.util.ArrayList;
import java.util.Random;

public class NovicePlayer extends BothPlayer{
    Random r = new Random();
    public NovicePlayer(String name, ArrayList<Card> hand, int score) {
        super(name, hand, score);
    }

    @Override
    public Card playCard() {
        int index=0;
        if (this.getHand().size()!=1) {
            index = r.nextInt(5) - 1;
        }
        return this.getHand().get(index);
    }
}
