import java.io.PrintWriter;

public class GamerCategoryException extends Exception{

    public GamerCategoryException(String message){
       super(message);
    }
    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }
}
