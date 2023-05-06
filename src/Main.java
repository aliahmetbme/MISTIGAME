import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

        while (true) {
            getPlayerCount(args[0]); // how many player will play

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
        /*
        System.out.print("Please enter point file name: ");
        checkFile(args[1])*/

        String[] gamersName = {firstGamerName,secondGamerName,thirdGamerName,forthGamerName};
        String[] gamersCategories = {firstGamerCategory,secondGamerCategory,thirdGamerCategory,forthGamerCategory};
        Player[] _gamers = {_firstGamer,_secondGamer,_thirdGamer,_fortGamer};

        int i = 0;


        for (String g : gamersName){
            try {

                gamersName[i] = args[ i +2 ]; // taking players name

                gamersCategories[i] = args[i + 3]; // to choose players category, we need to take this information

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
        // Player winner = new NovicePlayer("SIRAK",card,130,"EXPERT-BOTH");
        /* At the end of the game */
        //setTopTen(winner);

/*        ArrayList<Card> card1= new ArrayList<>();
        ArrayList<Card> card2= new ArrayList<>();
        ArrayList<Card> card3= new ArrayList<>();
        ArrayList<Card> card4= new ArrayList<>();
        Player player1= new HumanPlayer("a",card1,0,"HUMAN");
        Player player2= new ExpertPlayer("B",card2,0,"Expert");
        Player player3= new RegularPlayer("C",card3,0,"regular");
        Player player4= new NovicePlayer("D",card4,0,"novice");
        Player[] _gamers={player1,player2,player3,player4};*/
        //// oyun başlangıcı taslağı

        Deck deck= new Deck();
        deck.shuffle();
        deck.cut();
        ArrayList<Card> board=new ArrayList<>(4);
        board=deck.deal(4);
        ExpertPlayer.throwed=board;
        Player.topcard=board.get(3);
        try {
            while (deck.getNumCards() != 0) {
                for (Player gamer : _gamers) {
                    if(gamer==null){ break;}
                    gamer.setHand(deck.deal(4));

                }
                while (_gamers[_gamers.length - 1].getHand().size() != 0) {
                    for (Player gamer : _gamers) {
                        if(gamer==null){ continue;}
                        showboard(board);
                        Card throwcard = gamer.playCard();
                        if (Player.topcard != null) {
                            if (throwcard.getCardFace().equals(Player.topcard.getCardFace())) {
                                if (board.size() == 1) {
                                    System.out.print("MİŞTİ");
                                    gamer.setScore(gamer.getScore() + 10 + throwcard.getPoints() + Player.topcard.getPoints());
                                    ExpertPlayer.throwed.add(throwcard);
                                    Player.topcard = null;
                                    board.clear();
                                    gamer.getHand().remove(throwcard);
                                }
                            }else if (throwcard.getCardFace().equals(Player.topcard.getCardFace()) || throwcard.getCardFace().equals("JACK")) {
                                System.out.print("you got all the cards at the board");
                                for (Card card : board) {
                                    gamer.setScore(gamer.getScore() + card.getPoints());
                                }
                                gamer.setScore(gamer.getScore() + throwcard.getPoints());
                                Player.topcard = null;
                                ExpertPlayer.throwed.add(throwcard);
                                board.clear();
                                gamer.getHand().remove(throwcard);
                            }
                        else {
                                System.out.print(throwcard.toString() + " played by player\n");
                                board.add(throwcard);
                                Player.topcard = throwcard;
                                ExpertPlayer.throwed.add(throwcard);
                                gamer.getHand().remove(throwcard);  // BURASI BİTİNCE THROWEDA AYNI KARTI İKİ KERE EKLİYOR
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            System.out.print("something went wrong with the game please start the game again");
        }

        for (int x = 0; x < _gamers.length - 1; x++) {
            try {
                for (int j = 0; j < _gamers.length - x - 1; j++) {
                    if (_gamers[j].getScore() < _gamers[j + 1].getScore()) {
                        Player temp = _gamers[j];
                        _gamers[j] = _gamers[j + 1];
                        _gamers[j + 1] = (temp);
                    }
                }
            } catch (NullPointerException A) {
                break;
            }
        }

        for (Player p : _gamers){
            System.out.println(p.getName() + "   " + p.getScore());
        }

        setTopTen(_gamers[0]);


    }
    public static void showboard(ArrayList<Card> board){
        if(board.size()!=0){
            System.out.print("board:\n");
            for (Card card : board){
                System.out.print(card.toString()+"  ");
            }
        }else{
            System.out.print("board is empty");
        }
        System.out.print("\n");
    }

    public static void getPlayerCount(String args) {

        while (true) {
            try {
                playerCount = Integer.parseInt(args.trim());
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nError!!! : Please type your count as number (Input is not an Integer)");
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


    public static void checkFile(String args) {

        String pointFolderName = args.trim();
        while (true) {
            try {
                Scanner scan = new Scanner(new BufferedReader(new FileReader((pointFolderName + ".txt"))));
                break;
            } catch (FileNotFoundException e) {
                System.out.print("The file you entered are not available please try again: ");

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