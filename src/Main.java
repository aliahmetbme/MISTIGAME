import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scan = new Scanner(System.in);
        while (true){
            int name= 0;
            try {
               name = scan.nextInt();
               break;
            }catch (Exception e){
                System.out.println("You didnt enter user name correctly");
            }
        }
    }
}