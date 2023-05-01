import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    static int playerCount  = 0;
    public static void main(String[] args) {
        getPlayerCount();
    }
    public static int getPlayerCount(){
        Scanner scan = new Scanner(System.in);
        boolean isTrue ;
        while (true) {
            try {
                System.out.println("Enter player count :");
                playerCount = scan.nextInt();
                break;
            } catch (InputMismatchException e){
                System.out.println("Error: Input is not an integer.");
                scan.next();
            }

        }
        return playerCount;
    }
}