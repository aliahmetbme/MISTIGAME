import java.io.PrintWriter;

public class GamerCategoryException extends Exception{

    public GamerCategoryException(){
       super("Please enter category correctly");
    }
    public GamerCategoryException(String message){
        super(message);
    }

}
