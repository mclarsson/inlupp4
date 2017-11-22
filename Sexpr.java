
import java.util.Map;

public abstract class Sexpr {
    private String name;
    private int priority;

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
    //public abstract Sexpr eval();
}
