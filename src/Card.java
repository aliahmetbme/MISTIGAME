public class Card {
    private String suit;
    private String cardFace;
    private int points;

    public Card(String suit, String cardFace, int points) {
        this.suit = suit;
        this.cardFace = cardFace;
        this.points = points;
    }

    public Card() {
        this.suit = null;
        this.cardFace = null;
        this.points = 0;
    }


    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getCardFace() {
        return cardFace;
    }

    public void setCardFace(String cardFace) {
        this.cardFace = cardFace;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    @Override
    public String toString(){ return this.getCardFace()+this.getSuit();}
}
