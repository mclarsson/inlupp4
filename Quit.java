
import java.util.*;

public class Quit extends Command{

    public Boolean isQuit() {
        return true;
    }

    public String getName() {
        return "QUIT!";
    }
    public Sexpr eval(HashMap<String, Sexpr> variables) {
        return this;
    }
}
