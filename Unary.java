
public abstract class Unary extends Sexpr {
    protected Sexpr argument;

    /**
     * Constructor.
     *
     * @param argument Argument of expression.
     */
    public Unary(Sexpr argument) {
	this.argument = argument;
    }
}
