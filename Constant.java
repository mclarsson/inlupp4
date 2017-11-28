
import java.util.HashMap;

public class Constant extends Sexpr {

    private double value;

    /**
     * Constructor.
     *
     * @param value Value of constant.
     */
    public Constant(double value) {
	this.value = value;
    }

    /**
     * Returns name of Constant.
     *
     * @return Constant.
     */
    public String getName() {
	return this.toString();
    }

    /**
     * Returns value of constant.
     *
     * @return Value. 
     */
    public double getValue() {
	return value;
    }
    /**
     * Evaluate current constant.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> map) {
	return this;
    }

    /**
     * Converts constant to string.
     *
     * @return value to string. 
     */
    public String toString() {
	return Double.toString(value);
    }
}
