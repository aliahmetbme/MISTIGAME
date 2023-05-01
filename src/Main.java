import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name = null;
        while (true) {
            try {
                System.out.print("Enter your name: ");
                name = scan.nextLine();
                if (!name.matches("[a-zA-Z]+")) {
                    throw new InputMismatchException();
                }
                break;
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input. Please enter your name again.");
            }
        }
    }
}