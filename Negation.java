
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
	return Symbolic.negate(argument.eval(variables));
    }
}
