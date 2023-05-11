import java.io.PrintWriter;

public class ParametersError extends Exception{
    public ParametersError(){
        super("\n\nPlease provide parameters correctly \n " +
                "1 - Player Count \n" +
                "2 - Point file name \n" +
                "3 - Round Count \n" +
                "4 - Verbose mode \n" +
                "***** Provide player data as player count as. These are just examples *****\n"+
                "***** !!! Please provide player names' combined for example not 'ALİ AHMET' provide as !!! 'ALİAHMET' ******\n" +
                "***** Gamer categories are  'HUMAN' 'NOVICE-BOTH' 'EXPERT-BOTH' 'REGULAR-BOTH' \n" +
                "5 - First player name \n" +
                "6 - First player level  \n" +
                "7 - Second player name  \n" +
                "8 - Second player level  \n" +
                "9 - Third player name  \n" +
                "10 - Third player level  \n" +
                "11 - Forth player name  \n" +
                "12 - Forth player level  \n");
    }
    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }
}
