import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.WeakHashMap;

public class Main {
    static int playerCount = 0;
    static String firstGamer ;
    static String secondGamer ;
    static String thirdGamer ;
    static String forthGamer ;

    static String firstGamerCategory ;
    static String secondGamerCategory ;
    static String thirdGamerCategory ;
    static String forthGamerCategory ;


    static Player _firstGamer;
    static Player _secondGamer;
    static Player _thirdGamer;
    static Player _fortGamer;

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
                System.out.println("\n  Please enter correct player number  2 or 4");
                continue;
            }
        }

        switch (playerCount) {
            case 2:
                while (true) {
                    try {
                        System.out.print("Please enter first gamers' name ");
                        firstGamer = scan.next();
                        System.out.print("Please enter first gamers' category: ");
                        firstGamerCategory = scan.next();
                        checkPlayerCategory(firstGamerCategory);

                        System.out.print("Please enter second gamers' name ");
                        secondGamer = scan.next();
                        System.out.print("Please enter second gamers' category: ");
                        secondGamerCategory = scan.next();
                        checkPlayerCategory(secondGamerCategory);

                        break;
                    } catch (GamerCategoryException e) {
                        System.out.println("Error :  " + e.getMessage());
                    }
                }

        }


    }

    public static int getPlayerCount() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Please enter player count :");
                playerCount = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\n Error!!! : Please type your count as number (Input is not an Integer)");
                scan.next();
            }
        }
        return playerCount;
    }

    public static void checkPlayerCategory(String gamerCategory) throws GamerCategoryException {

        if (!gamerCategory.equals("HUMAN") && !gamerCategory.equals("EXPERT-BOTH") && !gamerCategory.equals("REGULAR-BOTH") && !gamerCategory.equals("NOVICE-BOTH")) {
            throw new GamerCategoryException("Please enter category correctly");
        }

    }
}