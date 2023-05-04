import java.util.ArrayList;
import java.util.Random;
public class RegularPlayer extends Player{
    Random r = new Random();
    private Card topcard= null;
    public RegularPlayer(String name, ArrayList<Card> hand, int score) {
        super(name, hand, score);
    }

    @Override
    public Card playCard() {
        try {
            Card t_card = null;
            if (topcard != null) {
                for (Card card : this.getHand()) {
                    if (topcard.getCardFace().equals(card.getCardFace())) {
                        return card;
                    }
                }
            }
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

    public void SetTopCard(Card card){
        this.topcard = card;
    }
    public void SetTopCard(){
        this.topcard = null;
    }
}
