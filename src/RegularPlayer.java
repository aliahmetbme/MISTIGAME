import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class RegularPlayer extends BothPlayer{
    Random r = new Random();
  /*  private Card topcard= null;*/
    public RegularPlayer(String name, ArrayList<Card> hand, int score, String level, ArrayList<Card> storedCards) {
        super(name, hand, score, level,storedCards);
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
            ArrayList<Integer> handpoints= new ArrayList<>();
            for( Card card: this.getHand()){
                handpoints.add(card.getPoints());
            }
            return this.getHand().get(handpoints.indexOf(Collections.min(handpoints)));
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
