import java.util.ArrayList;

public abstract class Player {
    static Card topcard;
    private String name;
    private ArrayList<Card> hand;
    private int score;
    private String level;
    private ArrayList<Card> storedCard;

    public Player(String name, ArrayList<Card> hand, int score, String level, ArrayList<Card> storedCard) {
        this.name = name;
        this.hand = hand;
        this.score = score;
        this.level = level;
        this.storedCard = storedCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }
    public Card playCard(){return null;};

    public void setLevel(String level) {
        this.level = level;
    }

    public ArrayList<Card> getStoredCard() {
        return storedCard;
    }

    public void setStoredCard(ArrayList<Card> cards){
        this.storedCard = cards;
    }

}
