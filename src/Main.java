import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("--Welcome to Mişti Game--");

        System.out.println("Please Enter how many player in game");
        String x = "Hello I am in entery try";

       int gamersCount;

        try {
            while (true) {
                gamersCount = scan.nextInt(); // how many player will play

                if (gamersCount == 4) {
                    System.out.println("4");
                    break;
                } else if (gamersCount == 2) {
                    System.out.println("2");
                    break;
                } else {
                    System.out.println("Please enter correct number");
                    continue;
                }
            }

            System.out.println("type your game parameters with a space between them");
            String gameParameters = scan.next();

            checkInputValues(gameParameters, gamersCount); // gamer sayısı değişecek orda

        } catch (InputMismatchException e){
            System.out.println("Please enter gamers count in correct form ");
        } catch (InputException inputException) {
            System.out.println("Your information is not understandable type ");
        }



        System.out.println("gerçek entter try");

    }

    public static void checkInputValues(String information, int informationCount) throws InputException {
        String[] inf = information.split(" ");
        if (inf.length != informationCount) {
           throw new InputException("") ;
           /* her parametre arasına bir boşluk koyarak yazdırdığımız farz ettim */
        }
    }


}