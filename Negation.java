
import java.util.HashMap;

public class Negation extends Unary {

    /**
     * constructor.
     *
     * @param argument Argument of operation.
     */
    public Negation(Sexpr argument) {
	super(argument);
    }

    /**
     * Returns name of operation.
     *
     * @return Operation name.
     */
    public String getName() {
	return "-";
    }

    /**
     * Evaluate current operation.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }

    /**
     * Concatenates argument with "-". 
     *
     * @return String with -(argument).
     */
    public String toString() {
	return "-(" + argument  + ")";
    }
}
