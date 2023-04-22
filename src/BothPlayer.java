import java.util.ArrayList;
import java.util.Random;

public abstract class BothPlayer extends Player{
    public BothPlayer(String name, ArrayList<Card> hand, int score) {
        super(name, hand, score);
    }
//    public static ArrayList<Card> cutDeck(ArrayList<Card> cards){
//        Random rd = new Random();
//        return cards;
//    }
//
//    public static ArrayList<Card> mixDeck(ArrayList<Card> cards){
//        return cards;
//    }
    //  Bunları bilgisayar yapacak diyor, bothlardan birinin yapıp yapmayacağından emin değilim


    public abstract Card playCard() ;
}
