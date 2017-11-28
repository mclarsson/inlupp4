
import java.util.HashMap;

public class Cos extends Unary {

    /**
     * constructor.
     *
     * @param argument Argument of operation.
     */
    public Cos(Sexpr argument) {
	super(argument);
    }

    /**
     * Returns name of operation.
     *
     * @return Operation name.
     */
    public String getName() {
	return "Cos";
    }

    /**
     * Evaluate current operation.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }
}
