import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(String name, ArrayList<Card> hand, int score, String level, ArrayList<Card> storedCards) {
        super(name, hand, score, level,storedCards);
    }

    public void showHand(){
        System.out.print("Your hand:\n");
        for (Card card : this.getHand()) {
            System.out.print(card.toString()+" ");
        }
        System.out.print("\n");
    }

    @Override
    public Card playCard() {
        Scanner sc = new Scanner(System.in);
        this.showHand();
        while (true) {
            int index=-2;
            try {
                System.out.println("Please enter an index to throw the card(1,2,3,4)");
                int user= Integer.parseInt(sc.nextLine());
                if((user==1 || user==2|| user==3|| user==4) && user<=this.getHand().size()){
                    index = user;
                }
                return this.getHand().get(index - 1);

            }catch(Exception e){
                System.out.println("Please enter a valid intiger number");
                this.showHand();
            }
        }
    }

}
