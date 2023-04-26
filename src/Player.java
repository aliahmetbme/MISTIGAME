import java.util.ArrayList;

public abstract class Player {
    private String name;
    private ArrayList<Card> hand;
    private int score;

    public Player(String name, ArrayList<Card> hand, int score) {
        this.name = name;
        this.hand = hand;
        this.score = score;
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
    public Card playCard(){return null;};

}
