import java.util.ArrayList;
import java.util.Random;

public class NovicePlayer extends BothPlayer{

    Random r = new Random();

    public NovicePlayer(String name, ArrayList<Card> hand, int score,String level) {
        super(name, hand, score, level);
    }
    @Override
    public Card playCard() {
        try {
            int index = 0;
            if (this.getHand().size() != 1) {
                index = r.nextInt(this.getHand().size()) ;
            }
            return this.getHand().get(index);
        }catch(Exception e){
            System.out.println("there is no card in this player's hand");
            return null;
        }
    }
}
