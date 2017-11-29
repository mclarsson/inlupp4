
import java.util.HashMap;

public class Exp extends Unary {

    /**
     * Constructor.
     *
     * @param argument Argument of operation.
     */
    public Exp(Sexpr argument) {
	super(argument);
    }

    /**
     * Returns name of operation.
     *
     * @return Operation name.
     */
    public String getName() {
	return "Exp";
    }

    /**
     * Evaluate current operation.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return Symbolic.exp(argument.eval(variables));
    }
}
