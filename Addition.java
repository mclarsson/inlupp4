
import java.util.HashMap;

public class Addition extends Binary {

    public Addition(Sexpr left, Sexpr right) {
	super(left, right);
    }

    public String getName() {
	return "+";
    }

    /**
     * String representation of Addition.
     *
     * @param p1 param
     * @return Expression as String
     */
    public String toString() {
	return "(" + left + " + " + right + ")";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }
}
