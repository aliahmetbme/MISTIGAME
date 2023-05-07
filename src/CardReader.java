import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class
CardReader {
    public static ArrayList<Card> readCardsFromFile(String filePath) {
        ArrayList<Card> cards = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split(",");
                String suit = attributes[0];
                String cardFace = attributes[1];
                int points = Integer.parseInt(attributes[2]);
                Card card = new Card(suit, cardFace, points);
                cards.add(card);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return cards;
    }
}

