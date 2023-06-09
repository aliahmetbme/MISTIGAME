import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    static File pointFile;
    static Player[] _gamers ;
    static int roundCount;
    static ArrayList<Card> card = new ArrayList<Card>(4);

    static ArrayList<Card> hand1 = new ArrayList<Card>(4);
    static ArrayList<Card> hand2 = new ArrayList<Card>(4);
    static ArrayList<Card> hand3 = new ArrayList<Card>(4);
    static ArrayList<Card> hand4 = new ArrayList<Card>(4);

    static ArrayList<Card> storeCards1 = new ArrayList<Card>(4);
    static ArrayList<Card> storeCards2 = new ArrayList<Card>(4);
    static ArrayList<Card> storeCards3 = new ArrayList<Card>(4);
    static ArrayList<Card> storeCards4 = new ArrayList<Card>(4);

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

            boolean verbose = true;
            try {
                getPlayerCount(args[0]); // how many player will play
                checkFile(args[1]);
                getRoundCount(args[2]);
                verbose = takeVerboseValue(args[3]);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nYou provided incomplete information");
                try {
                    throw new ParametersError();
                } catch (ParametersError ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }

            }

            String[] gamersName = {firstGamerName, secondGamerName, thirdGamerName, forthGamerName};
            String[] gamersCategories = {firstGamerCategory, secondGamerCategory, thirdGamerCategory, forthGamerCategory};
            _gamers = new Player[]{_firstGamer, _secondGamer, _thirdGamer, _fortGamer};

            ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>(4);

            hands.add(hand1);
            hands.add(hand2);
            hands.add(hand3);
            hands.add(hand4);

            ArrayList<ArrayList<Card>> storeCards = new ArrayList<ArrayList<Card>>(4);

            storeCards.add(storeCards1);
            storeCards.add(storeCards2);
            storeCards.add(storeCards3);
            storeCards.add(storeCards4);

            int i = 0;

            if ((args.length - 4) / 2 < playerCount || (args.length - 4) / 2 > playerCount) {
                System.err.println("\nYour players data were not given correctly \nThere are " + playerCount + " players but " + ((args.length - 4) / 2) + " players were provided");
                try {
                    throw new ParametersError();
                } catch (ParametersError ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }

            }
            while (true) {
                try {

                    gamersName[i / 2] = args[i + 4]; // taking players name

                    gamersCategories[i / 2] = args[i + 5]; // to choose players category, we need to take this information

                    if (gamersCategories[i / 2].equals("HUMAN")) {
                        humanCounter++;
                    }// if HUMAN player are chosen, human counter are increase
                    checkPlayerCategory(gamersCategories[i / 2]);
                    switch (gamersCategories[i / 2]) {
                        case "HUMAN" ->
                                _gamers[i / 2] = new HumanPlayer(gamersName[i / 2], hands.get(i / 2), 0, "HUMAN", storeCards.get(i / 2));
                        case "EXPERT-BOTH" ->
                                _gamers[i / 2] = new ExpertPlayer(gamersName[i / 2], hands.get(i / 2), 0, "EXPERT-BOTH", storeCards.get(i / 2));
                        case "REGULAR-BOTH" ->
                                _gamers[i / 2] = new RegularPlayer(gamersName[i / 2], hands.get(i / 2), 0, "REGULAR-BOTH", storeCards.get(i / 2));
                        case "NOVICE-BOTH" ->
                                _gamers[i / 2] = new NovicePlayer(gamersName[i / 2], hands.get(i / 2), 0, "NOVICE-BOTH", storeCards.get(i / 2));
                    }

                    i += 2;

                    if ((i / 2) == playerCount)
                        break; // the program create gamers as player count which entered in game as

                } catch (ArrayIndexOutOfBoundsException e) {
                    try {
                        throw new ParametersError();
                    } catch (ParametersError ex) {
                        System.err.println(ex.getMessage());
                        System.exit(1);
                    }
                }
            }

            System.out.println("Our Gamer are : ");
            int a = 1;
            for (Player p : _gamers) {
                try {
                    System.out.println(String.valueOf(a) + " " + p.getName() + " " + p.getLevel());
                    a++;
                } catch (NullPointerException e) {
                    break;
                }
            }
            int round = 1;
            //// oyun başlangıcı
            while (roundCount >= round) {
                try {
                    System.out.println("-- Welcome to Mişti Game " + round + "th round --");
                    Deck deck = new Deck(pointFile); // burda kart ekleme dosyası eklenince dosyayı parametre olarak vericez !!
                    deck.shuffle();
                    deck.cut();
                    ArrayList<Card> board = new ArrayList<>(4);
                    board = deck.deal(4);
                    ExpertPlayer.throwed = new ArrayList<>();
                    ExpertPlayer.throwed.addAll(board);
                    BothPlayer.topcard = board.get(3);
                    int hand_number = 0;
                    ArrayList<String> verboseList = new ArrayList<>();
                    Player lastgamer;
                    lastgamer = null;

                    while (deck.getNumCards() != 0) {

                        deck.dealcard(_gamers);

                        hand_number += 1;
                        /// dosyaya handi ve ellerdeki kartları ve scoreları yazdır.
                        verboseList.add("HAND " + hand_number);
                        for (Player gamer : _gamers) {
                            if (gamer == null) break;
                            verboseList.add(" " + gamer.getName() + ":  " + gamer.getHand().toString() + "  Score " + gamer.getScore());
                        }
                        verboseList.add("\n");
                        int turn_number = 0;
                        while (_gamers[0].getHand().size() != 0) {

                            turn_number += 1;
                            ///turnu dosyaya yazdır sonra kartları listede tut ve kartrları elsonunda bununyanına yazdır ve listeyi boşalt.
                            verboseList.add(turn_number + ".");

                            ArrayList<Card> cardList = new ArrayList<>();
                            for (Player gamer : _gamers) {
                                if (gamer == null) {
                                    break;
                                }

                                showBoard(board);

                                Card throwCard = gamer.playCard();
                                cardList.add(throwCard);

                                if (BothPlayer.topcard == null) {

                                    System.out.print(throwCard.toString() + " played  " + gamer.getName() + "\n\n");
                                    board.add(throwCard);
                                    BothPlayer.topcard = throwCard;
                                    ExpertPlayer.throwed.add(throwCard);
                                    gamer.getHand().remove(throwCard);

                                } else {

                                    if (throwCard.getCardFace().equals(BothPlayer.topcard.getCardFace())) {
                                        if (board.size() == 1) {
                                            System.out.print(throwCard.toString() + " played  " + gamer.getName() + "\n\n");
                                            System.out.print("!!! MİŞTİ !!!\n");
                                            gamer.setScore(gamer.getScore() + (throwCard.getPoints() + BothPlayer.topcard.getPoints()) * 5);
                                            ExpertPlayer.throwed.add(throwCard);
                                            BothPlayer.topcard = null;
                                            board.add(throwCard);
                                            gamer.getStoredCard().addAll(board);
                                            board.clear();
                                            gamer.getHand().remove(throwCard);
                                            lastgamer = gamer;
                                        } else {
                                            System.out.print(throwCard.toString() + " played  " + gamer.getName() + "\n\n");
                                            System.out.println("!!!" + gamer.getName() + " got all the cards at the board !!!\n");
                                            for (Card card : board) {
                                                gamer.setScore(gamer.getScore() + card.getPoints());
                                            }
                                            gamer.setScore(gamer.getScore() + throwCard.getPoints());
                                            BothPlayer.topcard = null;
                                            board.add(throwCard);
                                            ExpertPlayer.throwed.add(throwCard);
                                            gamer.getStoredCard().addAll(board);
                                            board.clear();
                                            gamer.getHand().remove(throwCard);
                                            lastgamer = gamer;
                                        }

                                    } else if (throwCard.getCardFace().equals("J")) {
                                        System.out.print(throwCard.toString() + " played  " + gamer.getName() + "\n\n");
                                        System.out.println("!!!" + gamer.getName() + " got all the cards at the board !!!\n");
                                        for (Card card : board) {
                                            gamer.setScore(gamer.getScore() + card.getPoints());
                                        }
                                        gamer.setScore(gamer.getScore() + throwCard.getPoints());
                                        BothPlayer.topcard = null;
                                        board.add(throwCard);
                                        ExpertPlayer.throwed.add(throwCard);
                                        gamer.getStoredCard().addAll(board);
                                        board.clear();
                                        gamer.getHand().remove(throwCard);
                                        lastgamer = gamer;

                                    } else {
                                        System.out.print(throwCard.toString() + " played by " + gamer.getName() + "\n\n");
                                        board.add(throwCard);
                                        BothPlayer.topcard = throwCard;
                                        ExpertPlayer.throwed.add(throwCard);
                                        gamer.getHand().remove(throwCard);
                                    }
                                }
                            }

                            verboseList.add(" " + cardList.toString() + "\n");
                            cardList.clear();
                        }
                    }
                    if (board.size() != 0) {
                        for (Card card : board) {
                            lastgamer.setScore((lastgamer.getScore() + card.getPoints()));
                        }
                        lastgamer.getStoredCard().addAll(board);
                    }
                    showBoard(board);

                    // verbose mode true ise dosyayı yazdır.
                    if (verbose) {
                        for (String string : verboseList) {
                            System.out.println(string);
                        }
                    }

                } catch (NullPointerException nullPointerException) {
                    System.out.println("the system catch null parameter ");
                } catch (Exception e) {
                    System.out.println("something went wrong with the game please start the game again");
                }

                System.out.println("In " + round + "th ");

                int index = 0;
                for (ArrayList<Card> cardx : storeCards) {
                    if (_gamers[index] == null) break;
                    System.out.println(_gamers[index].getName() + " " + cardx.toString() + " ");
                    index++;
                }

                Player[] array = new Player[4];
                for (int w = 0; w < array.length; w++) {
                    try {
                        array[w] = _gamers[w];
                    } catch (NullPointerException nullPointerException) {
                        break;
                    }
                }


                for (int x = 0; x < array.length - 1; x++) {
                    try {
                        for (int j = 0; j < array.length - x - 1; j++) {
                            if (array[j].getScore() < array[j + 1].getScore()) {
                                Player temp = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = (temp);
                            }
                        }
                    } catch (NullPointerException A) {
                        break;

                    }
                }

                for (Player p : array) {
                    try {
                        System.out.println(p.getName() + "   " + p.getScore());
                    } catch (NullPointerException e) {
                        break;
                    }
                }

                System.out.println("!!! congratulations  " + array[0].getName() + " !!!");
                System.out.println(array[0].getName() + " is winner " + round + "th round");
                setTopTen(array[0]);

                for (Player p : _gamers) {
                    try {
                        p.setScore(0);
                        p.getHand().clear();
                        p.getStoredCard().clear();
                        ExpertPlayer.throwed.clear();
                    } catch (NullPointerException e) {
                        break;
                    }
                }

                round++;
            }

    }
    public static void showBoard (ArrayList < Card > board) {
        if (board.size() != 0) {
            System.out.println("*******************************");
            System.out.print("board:\n");
            for (Card card : board) {
                System.out.print(card.toString() + "  ");
            }
            System.out.println();
            System.out.println("*******************************");
        } else {
            System.out.println("*******************************");
            System.out.print("board:\n");
            System.out.println("     ");
            System.out.println("*******************************");
        }
        System.out.print("\n");
    }
    public static void getPlayerCount (String args){
            try {
                playerCount = Integer.parseInt(args.trim());
                if (playerCount > 4 || playerCount < 2) {
                    throw new Exception();
                }
            } catch (NumberFormatException numberFormatException){
                System.out.println("\nError!!! : The value is too high");
                try {
                    throw new ParametersError();
                } catch (ParametersError ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }
            }
            catch (InputMismatchException e) {
                System.out.println("\nError!!! : Please type your count as number (Input is not an Integer)");
                try {
                    throw new ParametersError();
                } catch (ParametersError ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }
            } catch (Exception e) {
                System.out.println("\nError!!! : Player count cannot be more than 4 and less than 2 please provide player count less than 4");
                try {
                    throw new ParametersError();
                } catch (ParametersError ex) {
                    System.err.println(ex.getMessage());
                    System.exit(1);
                }
            }
    }
    public static void checkPlayerCategory (String gamerCategory)  {

            if (!gamerCategory.equals("HUMAN") && !gamerCategory.equals("EXPERT-BOTH") && !gamerCategory.equals("REGULAR-BOTH")
                    && !gamerCategory.equals("NOVICE-BOTH")) {
                try {
                    throw new GamerCategoryException();
                } catch (GamerCategoryException e) {
                    System.err.println(e.getMessage());
                    try {
                        throw new ParametersError();
                    } catch (ParametersError ex) {
                        System.err.println(ex.getMessage());
                        System.exit(1);
                    }
                }
            }

            if (humanCounter > 1) {
                try {
                    throw new GamerCategoryException("You cannot choose HUMAN player more than one time");
                } catch (GamerCategoryException e) {
                    System.err.println(e.getMessage());
                    try {
                        throw new ParametersError();
                    } catch (ParametersError ex) {
                        System.err.println(ex.getMessage());
                        System.exit(1);
                    }
                }
            }
    }
    public static void checkFile (String args) {
        String pointFolderName = args.trim();
        pointFile = new File(pointFolderName + ".txt");
        try {
            if (pointFile.exists()) {
                return;
            } else {
                throw new FileNotFoundException("The file you entered are not available please try again: ");
            }
        } catch (FileNotFoundException f){
            System.err.println(f.getMessage());
            try {
                throw new ParametersError();
            } catch (ParametersError ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        }

    }
    public static void setTopTen (Player winner) throws RuntimeException {
        FileWriter writer = null;
        try {
            File file = new File("Score.txt");

            if (!file.exists()){
                writer = new FileWriter(file);
                for (int p = 0 ; p < 10 ; p++)  {
                    writer.write("_" + "," + "_" + "," + "0"+ "\n");
                }
            }

            Scanner scanner = new Scanner(new BufferedReader(new FileReader("Score.txt")));
            Player[] winners = new Player[10]; // to store winners information easier, a player array are created
            int i = 0;

            while (scanner.hasNextLine()) {
                String[] information = scanner.nextLine().trim().split(",");
                if (information.length == 1 ) break;
                Player winwin = new Winners(information[0], card, Integer.parseInt(information[2].trim()), information[1],card);
                winners[i] = winwin;
                i++;
            }

            try {
                if (winners[i] == null) {
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
            }catch (NullPointerException e){
                System.out.println("null ki ne null");
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
            try {
                writer = new FileWriter("Score.txt");
                for (Player p : winners) {
                    if (p == null) {
                        writer.write("_,_,0\n");
                    } else {
                        writer.write(p.getName() + "," + p.getLevel() + "," + String.valueOf(p.getScore() + "\n"));
                    }
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getRoundCount (String args)   {
        try {
            roundCount = Integer.parseInt(args.trim());
        }  catch (NumberFormatException numberFormatException){
            System.out.println("\nError!!! : The value is too high");
            try {
                throw new ParametersError();
            } catch (ParametersError ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        }
        catch (InputMismatchException e) {
            System.out.println("\nError!!! : Please type your count as number (Input is not an Integer)");
            try {
                throw new ParametersError();
            } catch (ParametersError ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        }catch (Exception e) {
            System.err.println("Invalid parameter please provide Integer value for count number");
            try {
                throw new ParametersError();
            } catch (ParametersError ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        }
    }
    public static boolean takeVerboseValue(String args){
        if(args.toLowerCase().equals("true")) {
            return true;
        } else if (args.toLowerCase().equals("false")) {
            return false;
        } else {
            try {
                throw new ParametersError();
            } catch (ParametersError e) {
                System.err.println("Invalid parameter please provide correct value for verbose mode");
                System.exit(1);
                throw new RuntimeException(e);

            }
        }
    }
}
