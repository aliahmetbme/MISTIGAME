
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"HEARTS", "DIAMONDS", "CLUBS", "SPADES"};
        String[] cardFaces = {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};
        int[] points = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

        for (String suit : suits) {
            for (int i = 0; i < cardFaces.length; i++) {
                Card card = new Card(suit, cardFaces[i], points[i]);
                cards.add(card);
            }
        }
    }

    public Deck(String filePath) {
        cards = CardReader.readCardsFromFile(filePath);
    }

    
    public void shuffle() {
        Collections.shuffle(cards);
    }

    
    public void cut() {
        int cutIndex = (int) (Math.random() * cards.size());
        ArrayList<Card> cuttedCards = new ArrayList<>(cards.subList(cutIndex, cards.size()));
        cards.subList(cutIndex, cards.size()).clear();
        cards.addAll(0, cuttedCards);
    }

    
    public ArrayList<Card> deal(int numCards) {
        ArrayList<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            if (!cards.isEmpty()) {
                Card card = cards.remove(0);
                dealtCards.add(card);
            }
        }
        return dealtCards;
    }

    public int getNumCards() {
        return cards.size();
    }

    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }
}

