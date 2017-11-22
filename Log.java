
import java.util.HashMap;

public class Log extends Unary {

    public Log(Sexpr argument) {
	super(argument);
    }

    public String getName() {
	return "Log";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }

    public String toString() {
	return "Log(" + argument  + ")";
    }
}
