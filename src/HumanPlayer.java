import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, ArrayList<Card> hand, int score, String level) {
        super(name, hand, score,level);
    }

    public void showHand(){
        for (Card card : this.getHand()) {
            System.out.println(card.toString()+" ");
        }
    }

    @Override
    public Card playCard() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int index=-2;
            try {
                System.out.println("Please enter an index to throw the card(1,2,3,4)");
                int user=sc.nextInt();
                if(user==1 || user==2|| user==3|| user==4){
                    index=user;
                    return this.getHand().get(index-1);
                }
            }catch(Exception e){
                System.out.println("Please enter a valid intiger number");
                this.showHand();
            }
        }
    }

}
