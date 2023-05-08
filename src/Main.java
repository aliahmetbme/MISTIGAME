import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static int roundCount;
    static ArrayList<Card> card;
    static int playerCount = 0;
    static int humanCounter = 0;
    static String firstGamerName;
    static String secondGamerName;
    static String thirdGamerName;
    static String forthGamerName;
    static String firstGamerCategory;
    static String secondGamerCategory;
    static String thirdGamerCategory;
    static String forthGamerCategory;
    static Player _firstGamer;
    static Player _secondGamer;
    static Player _thirdGamer;
    static Player _fortGamer;

    public static void main(String[] args) {

        System.out.println("-- Welcome to Mişti Game --");
        Scanner scanner = new Scanner(System.in);

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
        checkFile(args[1]);

        String[] gamersName = {firstGamerName, secondGamerName, thirdGamerName, forthGamerName};
        String[] gamersCategories = {firstGamerCategory, secondGamerCategory, thirdGamerCategory, forthGamerCategory};
        Player[] _gamers = {_firstGamer, _secondGamer, _thirdGamer, _fortGamer};

        try {
            getRoundCount(args[2]);
        } catch (ParametersError e) {
            e.getMessage();
            System.exit(1);
        }

        int i = 0;

        for (String g : gamersName) {
            try {

                gamersName[i/2] = args[i+4]; // taking players name

                gamersCategories[i/2] = args[i+5]; // to choose players category, we need to take this information

                checkPlayerCategory(gamersCategories[i/2]);

                if (gamersCategories[i/2].equals("HUMAN"))
                    humanCounter++; // if HUMAN player are chosen, human counter are increase
                switch (gamersCategories[i/2]) {
                    case "HUMAN" -> _gamers[i/2] = new HumanPlayer(gamersName[i/2], card, 0, "HUMAN");
                    case "EXPERT-BOTH" -> _gamers[i/2] = new ExpertPlayer(gamersName[i/2], card, 0, "EXPERT-BOTH");
                    case "REGULAR-BOTH" -> _gamers[i/2] = new RegularPlayer(gamersName[i/2], card, 0, "REGULAR-BOTH");
                    case "NOVICE-BOTH" -> _gamers[i/2] = new NovicePlayer(gamersName[i/2], card, 0, "NOVICE-BOTH");
                }

                i += 2;

                if ((i/2) == playerCount) break; // the program create gamers as player count which entered in game as

            } catch (GamerCategoryException e) {
                System.out.println("Error :  " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                try {
                    throw new ParametersError("Please provide parameters correctly " +
                            "1 - Player Count 2 - Round Count 3 - First player name  " +
                            "4 - Second Player name Third Player name Fourth Player name");
                } catch (ParametersError ex) {
                    ex.getMessage();
                }
            }
        }

        for (Player p : _gamers) {
            try {
                System.out.println(p.getName() + " " + p.getHand() + " " + p.getScore() + " " + p.getLevel());
            } catch (NullPointerException e) {
                break;
            }
        }

        boolean verbose = takeVerboseValue(args[3]); /// kod testi için oluşturuldu.
        if(!verbose){
            System.out.println("Yanlış");
        }
        //// oyun başlangıcı

        try {
            Deck deck = new Deck(); // burda kart ekleme dosyası eklenince dosyayı parametre olarak vericez !!
            deck.shuffle();
            deck.cut();
            ArrayList<Card> board = new ArrayList<>(4);
            board = deck.deal(4);
            ExpertPlayer.throwed = new ArrayList<>();
            ExpertPlayer.throwed.addAll(board);
            Player.topcard = board.get(3);
            int hand_number = 0;
            ArrayList<String> verboseList = new ArrayList<>();

            while (deck.getNumCards() != 0) {

                for (Player gamer : _gamers) {
                    if (gamer == null) {
                        break;
                    }
                    gamer.setHand(deck.deal(4));
                }
                hand_number += 1;
                /// dosyaya handi ve ellerdeki kartları ve scoreları yazdır.
                verboseList.add("HAND " + hand_number);
                for (Player gamer : _gamers) {
                    if (gamer == null) break;
                    verboseList.add("; " + gamer.getName() + ":  " + gamer.getHand().toString() + "  Score " + gamer.getScore());
                }
                verboseList.add("\n");
                int turn_number = 0;
                while (_gamers[0].getHand().size() != 0) {

                    turn_number += 1;
                    ///turnu dosyaya yazdır sonra kartları listede tut ve kartrları elsonunda bununyanına yazdır ve listeyi boşalt.
                    verboseList.add(turn_number + ".");

                    ArrayList<Card> cardlist = new ArrayList<>();
                    for (Player gamer : _gamers) {


                        if (gamer == null) {
                            break;
                        }

                        showBoard(board);

                        Card throwcard = gamer.playCard();
                        cardlist.add(throwcard);

                        if (Player.topcard == null) {
                            System.out.println("5");
                            System.out.print(throwcard.toString() + " played  "+ gamer.getName() +"\n");
                            board.add(throwcard);
                            Player.topcard = throwcard;
                            ExpertPlayer.throwed.add(throwcard);
                            gamer.getHand().remove(throwcard);
                        } else if (Player.topcard != null) {
                            System.out.println("6");
                            if (throwcard.getCardFace().equals(Player.topcard.getCardFace())) {
                                if (board.size() == 1) {
                                    System.out.print("MİŞTİ");
                                    gamer.setScore(gamer.getScore() + 10 + throwcard.getPoints() + Player.topcard.getPoints());
                                    ExpertPlayer.throwed.add(throwcard);
                                    Player.topcard = null;
                                    board.clear();
                                    gamer.getHand().remove(throwcard);
                                } else {
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

                            } else if (throwcard.getCardFace().equals("JACK")) { // kartları dosyadan okumaya başlayınca burası değişecek

                                System.out.print("you got all the cards at the board");
                                for (Card card : board) {
                                    gamer.setScore(gamer.getScore() + card.getPoints());
                                }
                                gamer.setScore(gamer.getScore() + throwcard.getPoints());
                                Player.topcard = null;
                                ExpertPlayer.throwed.add(throwcard);
                                board.clear();
                                gamer.getHand().remove(throwcard);

                            } else {
                                System.out.print(throwcard.toString() + " played by "+gamer.getName()+"\n");
                                board.add(throwcard);
                                Player.topcard = throwcard;
                                ExpertPlayer.throwed.add(throwcard);
                                gamer.getHand().remove(throwcard);
                            }
                        }
                    }

                    verboseList.add(" " + cardlist.toString() + "\n");
                    cardlist.clear();
                }
            }
            // verbose mode true ise dosyayı yazdır.
            if (verbose) {
                for (String string : verboseList) {
                    System.out.println(string);

                }
            }
        } catch (NullPointerException nullPointerException){
            System.out.println("the system catch null parameter ");
        }
        catch (Exception e) {
            System.out.println("something went wrong with the game please start the game again");
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
        for (Player p : _gamers) {
            try {
                System.out.println(p.getName() + "   " + p.getScore());
            } catch (NullPointerException e) {
                break;
            }
        }

        System.out.println("!!! congratulations  " + _gamers[0].getName() + " !!!");
        setTopTen(_gamers[0]);

    }
    public static void showBoard (ArrayList < Card > board) {
        if (board.size() != 0) {
            System.out.println("*******************************");
            System.out.print("board:\n");
            System.out.println("*******************************");
            for (Card card : board) {
                System.out.print(card.toString() + "  ");
            }
        } else {
            System.out.println("*******************************");
            System.out.print("     ");
            System.out.println("*******************************");
        }
        System.out.print("\n");
    }
    public static void getPlayerCount (String args){

        while (true) {
            try {
                playerCount = Integer.parseInt(args.trim());
                break;
            } catch (InputMismatchException e) {
                System.out.println("\nError!!! : Please type your count as number (Input is not an Integer)");
            }
        }
    }
    public static void checkPlayerCategory (String gamerCategory) throws GamerCategoryException {

            if (!gamerCategory.equals("HUMAN") && !gamerCategory.equals("EXPERT-BOTH") && !gamerCategory.equals("REGULAR-BOTH") && !gamerCategory.equals("NOVICE-BOTH")) {
                throw new GamerCategoryException("Please enter category correctly");
            }

            if (humanCounter > 1) {
                humanCounter--;  // because program have already warned gamers, human count are decreased
                throw new GamerCategoryException("You cannot choose HUMAN player more than one time");
            }
    }
    public static void checkFile (String args) {
        String pointFolderName = args.trim();
        File file = new File(pointFolderName + ".txt");
        try {
            if (file.exists()) {
                System.out.println("The file exists.");
            } else {
                System.out.println("The file does not exist.");
                throw new FileNotFoundException("The file you entered are not available please try again: ");
            }
        } catch (FileNotFoundException f){
            f.getMessage();
        }

    }
    public static void setTopTen (Player winner) throws RuntimeException {
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
                    writer.write(p.getName() + "," + p.getLevel() + "," + String.valueOf(p.getScore() + "\n"));
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
    public static void getRoundCount (String args)  throws ParametersError {
        try {
            roundCount = Integer.parseInt(args.trim());
        } catch (Exception e) {
            System.err.println("Invalid parameter please provide Integer value");
            System.exit(1);
            throw new ParametersError("Please provide parameters correctly " +
                    "1 - Player Count 2 - Round Count 3 - First player name  " +
                    "4 - Second Player name Third Player name Fourth Player name");
        }
    }

    public static boolean takeVerboseValue(String args){
        return args.toLowerCase().equals("true");
    }
}
