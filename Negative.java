
import java.util.HashMap;

public class Negative extends Unary {

    public Negative(Sexpr argument) {
	super(argument);
    }

    public String getName() {
	return "-";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }

    public String toString() {
	return "-(" + argument  + ")";
    }
}
