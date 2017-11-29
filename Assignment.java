
import java.util.HashMap;

public class Assignment extends Binary {


    /**
     * Constructor.
     *
     * @param left  Left side of Assignment.
     * @param right Right side of Assignment.
     */
    public Assignment(Sexpr left, Sexpr right) {
	super(left, right);
    }

    /**
     * Return name of operation.
     *
     * @return Operation.
     */
    public String getName() {
	return "=";
    }

    /**
     * Evaluate current Assignment.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return Symbolic.assign(right.getName(), left, variables).eval(variables);
    }
}
