
import java.util.HashMap;

public class Subtraction extends Binary {

    public Subtraction(Sexpr left, Sexpr right) {
	super(left, right, "-");
    }

    public String getName() {
	return "-";
    }

    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return this;
    }
}
