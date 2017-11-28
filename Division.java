
import java.util.HashMap;

public class Division extends Binary {

    /**
     * Constructor.
     *
     * @param left  Sexpr left of Division.
     * @param right Sexpr right of Division.
     */
    public Division(Sexpr left, Sexpr right) {
	super(left, right);
    }

    /**
     * Return name of operation.
     *
     * @return Operation.
     */
    public String getName() {
	return "/";
    }

    public int priority() {
	return 1;
    }

    /**
     * Evaluate current Division.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }
}
