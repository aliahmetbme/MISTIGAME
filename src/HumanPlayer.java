import java.util.ArrayList;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score,level);
    }

    public static void showHand(ArrayList<Card> cards){
        for (Card card : cards) {
            System.out.println(card.getCardFace() + card.getSuit());
        }
    }

    @Override
    public Card playCard() {
        return null;
    }


}
