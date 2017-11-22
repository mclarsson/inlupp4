
import java.util.HashMap;

public class Exp extends Unary {

    public Exp(Sexpr argument) {
	super(argument);
    }

    public String getName() {
	return "Exp";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }

    public String toString() {
	return "Exp(" + argument  + ")";
    }
}
