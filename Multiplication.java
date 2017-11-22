
import java.util.HashMap;

public class Multiplication extends Binary {

    public Multiplication(Sexpr left, Sexpr right) {
	super(left, right);
    }

    public String getName() {
	return "*";
    }

    public String toString() {
	return "(" + left + " * " + right  + ")";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }
}
