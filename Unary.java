
public abstract class Unary extends Sexpr {
    protected Sexpr argument;
    protected String operation;

    /**
     * Constructor.
     *
     * @param argument Argument of expression.
     */
    public Unary(Sexpr argument) {
	this.argument = argument;
    }

    public String toString() {
	return getName() + "(" + argument + ")";
    }
}
