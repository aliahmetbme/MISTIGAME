import java.util.ArrayList;

public class ExpertPlayer extends BothPlayer{

    ArrayList<Card> throwed;
    private Card topcard= null;
    public ExpertPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score, level);
    }

    public void addThrowed(Card card){
        throwed.add(card);
    }
    public void SetTopCard(Card card){
        this.topcard = card;
    }
    public void SetTopCard(){
        this.topcard = null;
    }
    @Override
    public Card playCard() {
        if(topcard!=null){
            for (Card card : this.getHand()) {
                if (topcard.getCardFace().equals(card.getCardFace())) {
                    return card;
                }
            }
        }
        return null;
    }


}
