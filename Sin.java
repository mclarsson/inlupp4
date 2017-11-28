
import java.util.HashMap;

public class Sin extends Unary {

    /**
     * constructor.
     *
     * @param argument Argument of operation.
     */
    public Sin(Sexpr argument) {
	super(argument);
    }

    /**
     * Returns name of operation.
     *
     * @return Operation name.
     */
    public String getName() {
	return "Sin";
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
     * Concatenates argument with "Sin". 
     *
     * @return String with Sin(argument).
     */
    public String toString() {
	return "Sin(" + argument  + ")";
    }
}
