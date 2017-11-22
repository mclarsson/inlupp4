
public abstract class Unary extends Sexpr {
    protected Sexpr argument;

    public Unary(Sexpr argument) {
	this.argument = argument;
    }
}
