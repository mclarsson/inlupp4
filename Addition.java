
import java.util.HashMap;

public class Addition extends Binary {

    public Addition(Sexpr left, Sexpr right) {
	super(left, right, "+");
    }

    public String getName() {
	return "+";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }
}
