import java.util.ArrayList;
import java.util.Random;
public class RegularPlayer extends BothPlayer{
    Random r = new Random();
  /*  private Card topcard= null;*/
    public RegularPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score, level);
    }


    @Override
    public Card playCard() {
        try {
            if (topcard != null) {
                for (Card card : this.getHand()) {
                    if (topcard.getCardFace().equals(card.getCardFace())) {
                        return card;
                    }
                }
            }
            int index = 0;
            if (this.getHand().size() != 1) {
                index = r.nextInt(this.getHand().size()) ;
            }
            return this.getHand().get(index);
        }catch(Exception e){
            return this.getHand().get(0);
        }
    }

    /*public void SetTopCard(Card card){
        this.topcard = card;
    }
    public void SetTopCard(){
        this.topcard = null;
    }*/
}
