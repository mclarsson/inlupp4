
import java.util.HashMap;

public abstract class Sexpr {
    protected String name;
    protected int priority;

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
	return priority;
    }

    /**
     * Evaluate current expression.
     *
     * @return Result
     */
    public abstract Sexpr eval(HashMap<String, Sexpr> variables);
}
