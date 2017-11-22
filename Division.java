
import java.util.HashMap;

public class Division extends Binary {

    public Division(Sexpr left, Sexpr right) {
	super(left, right);
    }

    public String getName() {
	return "/";
    }

    public String toString() {
	return "(" + left + " / " + right  + ")";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }
}
