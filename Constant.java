
import java.util.HashMap;

public class Constant extends Sexpr {

    private double value;

    public Constant(double value) {
	this.value = value;
    }

    public String getName() {
	return this.toString();
    }

    public double getValue() {
	return value;
    }

    public Sexpr eval(HashMap<String, Sexpr> map) {
	return this;
    }

    public String toString() {
	return Double.toString(value);
    }
}
