
import java.util.HashMap;

public class Subtraction extends Binary {

    /**
     * Constructor.
     *
     * @param left  Sexpr left of Subtraction.
     * @param right Sexpr right of Subtraction.
     */
    public Subtraction(Sexpr left, Sexpr right) {
	super(left, right);
    }

    /**
     * Return name of operation.
     *
     * @return Operation.
     */
    public String getName() {
	return "-";
    }

    /**
     * Evaluate current Subtraction.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }
}
