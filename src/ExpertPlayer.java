import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ExpertPlayer extends BothPlayer{
    public static ArrayList<Card> throwed;
    /*private Card topcard= null;*/
    public ExpertPlayer(String name, ArrayList<Card> hand, int score, String level, ArrayList<Card> storedCards) {
        super(name, hand, score, level,storedCards);
    }
    @Override
    public Card playCard() {
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
                if (throwed.size()!=0){
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
                    int cardrepeat = Collections.max(repeat);
                    int minpoint= this.getHand().get(repeat.indexOf(cardrepeat)).getPoints();
                    cardindex=repeat.indexOf(cardrepeat);
                    for (int i=0;i<4;i++){
                        if(repeat.get(i)==cardrepeat){
                            if (this.getHand().get(i).getPoints()<minpoint){
                                cardindex=i;
                            }
                        }
                    }
                }else{
                    ArrayList<Integer> handpoints= new ArrayList<>();
                    for( Card card: this.getHand()){
                        handpoints.add(card.getPoints());
                    }
                    return this.getHand().get(handpoints.indexOf(Collections.min(handpoints)));
                }
                /// throwed ve puana göre kısmı ekle
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
                int cardrepeat= Collections.max(repeat);
                int minpoint= this.getHand().get(repeat.indexOf(cardrepeat)).getPoints();
                cardindex=repeat.indexOf(cardrepeat);
                for (int i=0;i<4;i++){
                    if(repeat.get(i)==cardrepeat){
                        if (this.getHand().get(i).getPoints()<minpoint){
                            cardindex=i;
                        }
                    }
                }
            }
            else{
                ArrayList<Integer> handpoints= new ArrayList<>();
                for( Card card: this.getHand()){
                    handpoints.add(card.getPoints());
                }
                return this.getHand().get(handpoints.indexOf(Collections.min(handpoints)));
            }

            return this.getHand().get(cardindex);
        }catch(Exception e){
            return this.getHand().get(0);
        }
    }


}
