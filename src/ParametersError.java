import java.io.PrintWriter;

public class ParametersError extends Exception{
    public ParametersError(){
        super("Please provide parameters correctly " +
                "1 - Player Count \n" +
                "2 - Round Count \n" +
                "3 - Verbose mode \n" +
                "4 - First player name \n" +
                "5 - First player level \n" +
                "6 - Second player name  \n" +
                "7 - Second player level \n" +
                "8 - Third player name  \n" +
                "9 - Third player level \n" +
                "10 - Forth player name  \n" +
                "11 - Forth player level \n");
    }
    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }
}
