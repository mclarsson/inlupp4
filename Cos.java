
import java.util.HashMap;

public class Cos extends Unary {

    public Cos(Sexpr argument) {
	super(argument);
    }

    public String getName() {
	return "Cos";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }

    public String toString() {
	return "Cos(" + argument  + ")";
    }
}
