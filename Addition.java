
import java.util.HashMap;

public class Addition extends Binary {

    /**
     * Constructor.
     *
     * @param left  Sexpr left of addition.
     * @param right Sexpr right of addition.
     */

    public Addition(Sexpr left, Sexpr right) {
	super(left, right);
    }

    /**
     * Return name of operation.
     *
     * @return Operation.
     */
    public String getName() {
	return "+";
    }

    /**
     * Evaluate current Addition.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return Symbolic.add(left.eval(variables), right.eval(variables));
    }
}
