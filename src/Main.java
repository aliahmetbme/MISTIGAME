import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int playerCount  = 0;
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("--Welcome to Mişti Game--");

        System.out.println("Please Enter how many player in game");


        while (true) {
            getPlayerCount(); // how many player will play

            if (playerCount == 4) {
                System.out.println("4");
                break;
            } else if (playerCount == 2) {
                System.out.println("2");
                break;
            } else {
                System.out.println("Please enter correct number");
                continue;
            }
        }

    }
    public static int getPlayerCount() {
            Scanner scan = new Scanner(System.in);
            boolean isTrue;
            while (true) {
                try {
                    System.out.print("Please enter player count :");
                    playerCount = scan.nextInt();

                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Error!!! : Please type your count as number (Input is not an integer)");
                    scan.next();
                }
            }
            return playerCount;

        }

}