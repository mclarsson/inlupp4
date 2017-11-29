
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

    /**
     * Concatenates left, right and operation of expression.
     *
     * @return Expression in string-form.
     */
    public String toString() {

	return "(" + left + " " + getName() + " " + right + ")";
	/*
	String s;

	if (left.priority() > 0) {
	    s = "(" + left + ")";
	} else {
	    s = left.toString();
	}

	s += " " + getName() + " ";

	if (right.priority() > 0) {
	    s += "(" + right + ")";
	} else {
	    s += right.toString();
	}

	return s;*/
    }
}
