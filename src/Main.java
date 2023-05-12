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
        getPlayerCount(args[0]); // how many player will play

        checkFile(args[1]);

        String[] gamersName = {firstGamerName, secondGamerName, thirdGamerName, forthGamerName};
        String[] gamersCategories = {firstGamerCategory, secondGamerCategory, thirdGamerCategory, forthGamerCategory};
        _gamers = new Player[]{_firstGamer, _secondGamer, _thirdGamer, _fortGamer};
        ArrayList<ArrayList<Card>> hands  = new ArrayList<ArrayList<Card>>(4);

        hands.add(hand1);
        hands.add(hand2);
        hands.add(hand3);
        hands.add(hand4);

        getRoundCount(args[2]);

        boolean verbose = takeVerboseValue(args[3]);

        int i = 0;

        if ((args.length - 4) / 2 < playerCount || (args.length - 4) / 2 > playerCount){
            System.err.println("Your players data were not given correctly \nThere are " + playerCount +" players but " +((args.length - 4) / 2 )+" players were provided");
            try{
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
                    case "HUMAN" -> _gamers[i / 2] = new HumanPlayer(gamersName[i / 2], hands.get(i/2), 0, "HUMAN");
                    case "EXPERT-BOTH" -> _gamers[i / 2] = new ExpertPlayer(gamersName[i / 2], hands.get(i/2), 0, "EXPERT-BOTH");
                    case "REGULAR-BOTH" -> _gamers[i / 2] = new RegularPlayer(gamersName[i / 2], hands.get(i/2), 0, "REGULAR-BOTH");
                    case "NOVICE-BOTH" -> _gamers[i / 2] = new NovicePlayer(gamersName[i / 2], hands.get(i/2), 0, "NOVICE-BOTH");
                }

                i += 2;

                if ((i / 2) == playerCount) break; // the program create gamers as player count which entered in game as

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
             System.out.println("-- Welcome to Mişti Game "+round+"th round --");
             Deck deck = new Deck(pointFile); // burda kart ekleme dosyası eklenince dosyayı parametre olarak vericez !!
             deck.shuffle();
             deck.cut();
             ArrayList<Card> board = new ArrayList<>(4);
             board = deck.deal(4);
             ExpertPlayer.throwed = new ArrayList<>();
             ExpertPlayer.throwed.addAll(board);
             Player.topcard = board.get(3);
             int hand_number = 0;
             ArrayList<String> verboseList = new ArrayList<>();
             Player lastgamer ;
             lastgamer=null;

             while (deck.getNumCards() != 0) {

//                 for(int d=0;d<4;d++) {
//                     for (Player gamer : _gamers) {
//                         if (gamer == null) {
//                             break;                       OYUNU PATLATIYOR
//                         }
//                         System.out.println(gamer.getHand().add(deck.dealcard()));    // düzelt
//                     }
//                 }
                /* for (Player gamer :_gamers){
                     if (gamer == null) break;
                     gamer.setHand(deck.deal(4));
                 }
*/
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

                         if (Player.topcard == null) {

                             System.out.print(throwCard.toString() + " played  " + gamer.getName() + "\n\n");
                             board.add(throwCard);
                             Player.topcard = throwCard;
                             ExpertPlayer.throwed.add(throwCard);
                             gamer.getHand().remove(throwCard);

                         } else {

                             if (throwCard.getCardFace().equals(Player.topcard.getCardFace())) {
                                 if (board.size() == 1) {
                                     System.out.print(throwCard.toString() + " played  " + gamer.getName() + "\n\n");
                                     System.out.print("MİŞTİ\n");
                                     gamer.setScore(gamer.getScore() + 10 + throwCard.getPoints() + Player.topcard.getPoints());
                                     ExpertPlayer.throwed.add(throwCard);
                                     Player.topcard = null;
                                     board.clear();
                                     gamer.getHand().remove(throwCard);
                                     lastgamer=gamer;
                                 } else {
                                     System.out.print(throwCard.toString() + " played  " + gamer.getName() + "\n\n");
                                     System.out.println("you got all the cards at the board\n");
                                     for (Card card : board) {
                                         gamer.setScore(gamer.getScore() + card.getPoints());
                                     }
                                     gamer.setScore(gamer.getScore() + throwCard.getPoints());
                                     Player.topcard = null;
                                     ExpertPlayer.throwed.add(throwCard);
                                     board.clear();
                                     gamer.getHand().remove(throwCard);
                                     lastgamer=gamer;
                                 }

                             } else if (throwCard.getCardFace().equals("J")) { // kartları dosyadan okumaya başlayınca burası değişecek
                                 System.out.print(throwCard.toString() + " played  " + gamer.getName() + "\n\n");
                                 System.out.println("you got all the cards at the board\n");
                                 for (Card card : board) {
                                     gamer.setScore(gamer.getScore() + card.getPoints());
                                 }
                                 gamer.setScore(gamer.getScore() + throwCard.getPoints());
                                 Player.topcard = null;
                                 ExpertPlayer.throwed.add(throwCard);
                                 board.clear();
                                 gamer.getHand().remove(throwCard);
                                 lastgamer=gamer;

                             } else {
                                 System.out.print(throwCard.toString() + " played by " + gamer.getName() + "\n\n");
                                 board.add(throwCard);
                                 Player.topcard = throwCard;
                                 ExpertPlayer.throwed.add(throwCard);
                                 gamer.getHand().remove(throwCard);
                             }
                         }
                     }

                     verboseList.add(" " + cardList.toString() + "\n");
                     cardList.clear();
                 }
             }
             if(board.size()!=0){
                 for(Card card:board){
                     lastgamer.setScore((lastgamer.getScore()+card.getPoints()));
                 }
             }

             // verbose mode true ise dosyayı yazdır.
             if (verbose) {
                 for (String string : verboseList) {
                     System.out.println(string);
                 }
             }

             showBoard(board);

         } catch (NullPointerException nullPointerException) {
             System.out.println("the system catch null parameter ");
         } catch (Exception e) {
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
         System.out.println(_gamers[0].getName() + " is winner " + round + "th round");
         setTopTen(_gamers[0]);
         round ++;
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
            if (playerCount > 4){
                throw new Exception();
            }
        } catch (InputMismatchException e) {
            System.out.println("\nError!!! : Please type your count as number (Input is not an Integer)");
            try {
                throw new ParametersError();
            } catch (ParametersError ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("\nError!!! : Player count cannot be more than 4 please provide player count less than 4");
            try {
                throw new ParametersError();
            } catch (ParametersError ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        }
    }
    public static void checkPlayerCategory (String gamerCategory)  {

            if (!gamerCategory.equals("HUMAN") && !gamerCategory.equals("EXPERT-BOTH") && !gamerCategory.equals("REGULAR-BOTH") && !gamerCategory.equals("NOVICE-BOTH")) {
                try {
                    throw new GamerCategoryException("Please enter category correctly");
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
                for (int p = 0 ; p <= 10 ; p++)  {
                    writer.write("_" + "," + "_" + "," + "0"+ "\n");
                }
            }
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getRoundCount (String args)   {
        try {
            roundCount = Integer.parseInt(args.trim());
        } catch (Exception e) {
            System.err.println("Invalid parameter please provide Integer value for count number");
            try {
                throw new ParametersError();
            } catch (ParametersError ex) {
                System.err.println(e.getMessage());
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
