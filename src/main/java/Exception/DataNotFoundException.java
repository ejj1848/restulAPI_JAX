package Exception;


/**
 * Created by ericjohn1 on 11/9/2016.
 */
public class DataNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 7552106847893048565L;

    public DataNotFoundException(String message){
        super(message);
    }
}
