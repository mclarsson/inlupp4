
import java.util.*;

public class Quit extends Command{

    /**
     * Returns true;
     *
     */
    public Boolean isQuit() {
        return true;
    }

    /**
     * Returns name of command.
     *
     * @return Command name.
     */
    public String getName() {
        return "QUIT!";
    }
    public Sexpr eval(HashMap<String, Sexpr> variables) {
        return this;
    }
}
