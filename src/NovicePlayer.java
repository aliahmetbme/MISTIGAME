import java.util.ArrayList;
import java.util.Random;

public class NovicePlayer extends BothPlayer{

    Random r = new Random();

    public NovicePlayer(String name, ArrayList<Card> hand, int score,String level) {
        super(name, hand, score, level);
    }
    @Override
    public Card playCard() {

        Random r = new Random();
        int index=0;
        if (this.getHand().size()!=1) {
            index = r.nextInt(5) - 1;
        }
        return this.getHand().get(index);
    }
}
