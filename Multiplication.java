
import java.util.HashMap;

public class Multiplication extends Binary {

    /**
     * Constructor.
     *
     * @param left  Sexpr left of Multiplication.
     * @param right Sexpr right of Multiplication.
     */
    public Multiplication(Sexpr left, Sexpr right) {
	super(left, right);
    }

    /**
     * Return name of operation.
     *
     * @return Operation.
     */
    public String getName() {
	return "*";
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
