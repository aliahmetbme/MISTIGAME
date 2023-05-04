import java.util.ArrayList;
import java.util.Random;

public class NovicePlayer extends Player{
    Random r = new Random();
    public NovicePlayer(String name, ArrayList<Card> hand, int score) {
        super(name, hand, score);
    }

    @Override
    public Card playCard() {
        try {
            int index = 0;
            if (this.getHand().size() != 1) {
                index = r.nextInt(this.getHand().size()) - 1;
            }
            return this.getHand().get(index);
        }catch(Exception e){
            System.out.println("there is no card in this player's hand");
            return null;
        }
    }
}
