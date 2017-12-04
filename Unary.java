
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
    /**
     * Returns name and argument of operation.
     *
     * @return Operation and argument.
     */
    public String toString() {
	return getName() + "(" + argument + ")";
    }
}
