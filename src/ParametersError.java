import java.io.PrintWriter;

public class ParametersError extends Exception{
    public ParametersError(String message){
        super(message);
    }
    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }
}
