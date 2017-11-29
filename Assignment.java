
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

    public int priority() {
	return 1;
    }

    /**
     * Evaluate current Assignment.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	String name = right.getName();
	Sexpr value = left.eval(variables);
	return Symbolic.assign(name, value, variables);
    }
}
