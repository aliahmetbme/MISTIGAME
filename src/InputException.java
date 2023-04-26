import java.io.PrintWriter;

public class InputException extends Exception{

    public InputException(String message){
       super(message);
    }
    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }
}
