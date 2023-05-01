import java.io.*;
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

        System.out.print("Please enter point file name: ");
        checkFile();

        String[] gamersName = {firstGamerName,secondGamerName,thirdGamerName,forthGamerName};
        String[] gamersCategories = {firstGamerCategory,secondGamerCategory,thirdGamerCategory,thirdGamerCategory,forthGamerCategory};
        Player[] _gamers = {_firstGamer,_secondGamer,_thirdGamer,_fortGamer};
        
        int i = 0;


        for (String g : gamersName){
            try {
                System.out.print("Please enter " + (i + 1) + "th gamers' name ");
                gamersName[i] = scan.next(); // taking players name

                System.out.print("Please enter " + (i + 1) + "th gamers' category ");
                gamersCategories[i] = scan.next(); // to choose players category, we need to take this information

                checkPlayerCategory(gamersCategories[i]);

                if (gamersCategories[i].equals("HUMAN")) humanCounter++; // if HUMAN player are chosen, human counter are incase
                switch (gamersCategories[i]) {
                    case "HUMAN" -> _gamers[i] = new HumanPlayer(gamersName[i], card, 0,"HUMAN");
                    case "EXPERT-BOTH" -> _gamers[i] = new ExpertPlayer(gamersName[i], card, 0,"EXPERT-BOTH");
                    case "REGULAR-BOTH" -> _gamers[i] = new RegularPlayer(gamersName[i], card, 0,"REGULAR-BOTH");
                    case "NOVICE-BOTH" -> _gamers[i] = new NovicePlayer(gamersName[i], card, 0,"NOVICE-BOTH");
                }
                i++;
                if (i == playerCount) break; // the program create gamers as player count which entered in game as

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
        // just example
        Player winner = new NovicePlayer("SIRAK",card,130,"EXPERT-BOTH");
        /* At the end of the game */
        setTopTen(winner);

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
            humanCounter --;  // because program have already warned gamers, human count are decreased
            throw new GamerCategoryException("You cannot choose HUMAN player more than one time");
        }
    }


    public static void checkFile() {
        Scanner scanner = new Scanner(System.in);
        String pointFolderName = scanner.next().trim();
        while (true) {
            try {
                Scanner scan = new Scanner(new BufferedReader(new FileReader((pointFolderName + ".txt"))));
                break;
            } catch (FileNotFoundException e) {
                System.out.print("The file you entered are not available please try again: ");
                scanner.next();
            }
        }
    }

    public static void setTopTen(Player winner) {
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader("Score.txt")));
            Player[] winners = new Player[10]; // to store winners information easier, a player array are created
            int i = 0;

            while (scanner.hasNextLine()) {
                String[] information = scanner.nextLine().trim().split(",");
                Player winwin = new Winners(information[0], card, Integer.parseInt(information[2].trim()), information[1]);
                winners[i] = winwin;
                i++;
            }

            try {
                if (winners[i] == null && i != 8) {
                    winners[i] = winner;
                } else {
                    if (winner.getScore() > winners[i].getScore()) {
                        winners[i] = winner;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (winner.getScore() > winners[9].getScore()) {
                    winners[9] = winner;
                }
            }

            for (int x = 0; x < winners.length - 1; x++) {
                try {
                    for (int j = 0; j < winners.length - x - 1; j++) {
                        if (winners[j].getScore() < winners[j + 1].getScore()) {
                            Player temp = winners[j];
                            winners[j] = winners[j + 1];
                            winners[j + 1] = (temp);
                        }
                    }
                } catch (NullPointerException A) {
                    break;
                }
            }

            FileWriter writer = null;
            try {
                writer = new FileWriter("Score.txt");
                for (Player p : winners) {
                    if (p == null) break;
                    writer.write(p.getName() + "," + p.getLevel() + String.valueOf(p.getScore() + "," + "\n"));
                }
            } catch (IOException E) {
                System.out.println("Error : the file cannot be opened");
            } finally {
                try {
                    assert writer != null;
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}