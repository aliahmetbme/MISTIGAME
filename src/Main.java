import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scan = new Scanner(System.in);
        while (true){
            try {
                String name = scan.nextLine();
                break;
            }catch (InputMismatchException e){
                System.out.println("You didnt enter user name correctly");
            }
        }
    }
}