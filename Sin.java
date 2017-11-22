
import java.util.HashMap;

public class Sin extends Unary {

    public Sin(Sexpr argument) {
	super(argument);
    }

    public String getName() {
	return "Sin";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }

    public String toString() {
	return "Sin(" + argument  + ")";
    }
}
