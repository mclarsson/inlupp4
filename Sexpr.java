
import java.util.HashMap;

public abstract class Sexpr {
    protected String name;

    /**
     * Return name of Sexpr.
     *
     * @return Name of expression
     */
    public abstract String getName();

    /**
     * Get the priority of the expression.
     *
     * @return Priority
     */
    public int priority() {
	return 0;
    }

    /**
     * Determine if expression is a constant.
     *
     * @return True if is constant, false otherwise
     */
    public Boolean isConstant() {
	return false;
    }

    public double getValue() {
	return 0;
    }

    /**
     * Evaluate current expression.
     *
     * @return Result
     */
    public abstract Sexpr eval(HashMap<String, Sexpr> variables);
}
