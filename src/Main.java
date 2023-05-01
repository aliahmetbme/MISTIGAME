import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.WeakHashMap;

public class Main {
    static ArrayList<Card> card;
    static int playerCount = 0;
    static int humanCounter = 0;
    static String firstGamerName ;
    static String secondGamerName ;
    static String thirdGamerName ;
    static String forthGamerName ;
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

        System.out.println("-- Welcome to Mişti Game --");

        System.out.print("Please Enter how many player in game: ");


        while (true) {
            getPlayerCount(); // how many player will play

            if (playerCount == 4) {
                System.out.println("4");
                break;
            } else if (playerCount == 2) {
                System.out.println("2");
                break;
            } else {
                System.out.println("\nPlease enter correct player number  2 or 4");
            }
        }
             
        String[] gamersName = {firstGamerName,secondGamerName,thirdGamerName,forthGamerName};
        String[] gamersCategories = {firstGamerCategory,secondGamerCategory,thirdGamerCategory,thirdGamerCategory,forthGamerCategory};
        Player[] _gamers = {_firstGamer,_secondGamer,_thirdGamer,_fortGamer};
        
        int i = 0;


        for (String g : gamersName){
            try {
                System.out.print("Please enter " + (i + 1) + "th gamers' name ");
                gamersName[i] = scan.next();

                System.out.print("Please enter " + (i + 1) + "th gamers' category ");
                gamersCategories[i] = scan.next();

                checkPlayerCategory(gamersCategories[i]);

                if (gamersCategories[i].equals("HUMAN")) humanCounter++;
                switch (gamersCategories[i]) {
                    case "HUMAN" -> _gamers[i] = new HumanPlayer(gamersName[i], card, 0);
                    case "EXPERT-BOTH" -> _gamers[i] = new ExpertPlayer(gamersName[i], card, 0);
                    case "REGULAR-BOTH" -> _gamers[i] = new RegularPlayer(gamersName[i], card, 0);
                    case "NOVICE-BOTH" -> _gamers[i] = new NovicePlayer(gamersName[i], card, 0);
                }
                i++;
                if (i == playerCount) break;

            } catch (GamerCategoryException e){
                System.out.println("Error :  " + e.getMessage());
            }
        }

        for (Player p : _gamers){
            try {
                System.out.println(p.getName() + " " + p.getHand() + " " + p.getScore());
            }catch (NullPointerException e){
                break;
            }
        }
    }

    public static void getPlayerCount() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                playerCount = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nError!!! : Please type your count as number (Input is not an Integer)");
                scan.next();
            }
        }
    }

    public static void checkPlayerCategory(String gamerCategory) throws GamerCategoryException {

        if (!gamerCategory.equals("HUMAN") && !gamerCategory.equals("EXPERT-BOTH") && !gamerCategory.equals("REGULAR-BOTH") && !gamerCategory.equals("NOVICE-BOTH")) {
            throw new GamerCategoryException("Please enter category correctly");
        }

        if (humanCounter > 1){
            humanCounter --;
            throw new GamerCategoryException("You cannot choose HUMAN player more than one time");
        }
    }
}