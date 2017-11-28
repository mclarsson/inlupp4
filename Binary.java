
public abstract class Binary extends Sexpr {
    protected Sexpr left;
    protected Sexpr right;
    protected String operation;

    /**
     * Constructor.
     *
     * @param left Left side of expression
     * @param right Right side of expression
     */
    public Binary(Sexpr left, Sexpr right, String op) {
	this.left = left;
	this.right = right;
	this.operation = op;

        
    }

    /**
     * Concatenates left, right and operation of expression.
     *
     * @return Expression in string-form. 
     */
    public String toString() {
	return left + " " + this.operation +  " " + right;
    }
}
