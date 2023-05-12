import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Deck {
    private ArrayList<Card> cards;
    private File pointFile;

    public Deck(File pointFile) {

        this.pointFile = pointFile;

        cards = new ArrayList<>();
        cards = readCardsFromFile();

    }

    public ArrayList<Card> readCardsFromFile() {
        ArrayList<Card> cards = new ArrayList<>();

        try {
            File file = getPointFile();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split(",");
                String suit = attributes[0];
                String cardFace = attributes[1];
                int points = Integer.parseInt(attributes[2]);
                Card card = new Card(suit, cardFace, points);
                cards.add(card);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return cards;
    }


    public void shuffle() {Collections.shuffle(cards);}

    
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

    public File getPointFile() {
        return pointFile;
    }
    public void dealcard(Player[] gamers) {
        int i=0;
        while(i<4) {
            for (Player gamer : gamers) {
                if (gamer == null) break;
                Card card = cards.remove(0);
                gamer.getHand().add(card);
            }
            i+=1;
        }


    }

}

