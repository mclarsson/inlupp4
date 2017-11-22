
public abstract class Binary extends Sexpr {
    protected Sexpr left;
    protected Sexpr right;

    /**
     * Constructor.
     *
     * @param left Left side of expression
     * @param right Right side of expression
     */
    public Binary(Sexpr left, Sexpr right) {
	this.left = left;
	this.right = right;
    }
}
