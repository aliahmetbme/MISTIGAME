import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ExpertPlayer extends BothPlayer{
    static ArrayList<Card> throwed;
    /*private Card topcard= null;*/
    public ExpertPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score, level);
    }

 /*   public void addThrowed(Card card){
        throwed.add(card);
    }
    public void SetTopCard(Card card){
        this.topcard = card;
    }
    public void SetTopCard(){
        this.topcard = null;
    }*/
    @Override
    public Card playCard() {
        Random r=new Random();
        int cardindex=-1;
        try {
            //if there is one card in the hand
            if (this.getHand().size()==1){
                cardindex=0;
            }//if there is one card in the hand that matches with the top card at the board
            else if (topcard != null) {
                for (Card card : this.getHand()) {
                    if (topcard.getCardFace().equals(card.getCardFace())) {
                        return card;
                    }
                }
            }//to check the repeated cards in the hand to throw
            else if (throwed.size()!=0){
                ArrayList<Integer> repeat = new ArrayList<Integer>(Collections.nCopies(this.getHand().size(), 0));
                int index=0;
                for (Card card : this.getHand()) {
                    for(Card t_card:throwed) {
                        if (t_card.getCardFace().equals(card.getCardFace())) {
                            repeat.set(index, repeat.get(index) + 1);
                        }
                    }
                    index+=1;
                }
                cardindex= repeat.indexOf(Collections.max(repeat));
            }/*else if (this.getHand().size()!=0){
                cardindex = r.nextInt(this.getHand().size());
            }*/
            return this.getHand().get(cardindex);
        }catch(Exception e){
            return this.getHand().get(0);
        }
    }
}
