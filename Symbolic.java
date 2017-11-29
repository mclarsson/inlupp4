
public class Symbolic {

    /**
     * Add two expressions together.
     *
     * @param left Left hand side of addition
     * @param right Right hand side of addition
     * @return Result of addition
     */
    public static Sexpr add(Sexpr left, Sexpr right) {

	if (left.isConstant() && right.isConstant()) {
	    return new Constant(left.getValue() + right.getValue());
	}

	return new Addition(left, right);
    }

    /**
     * Subtract one expression from the other.
     *
     * @param left Left hand side of subtraction
     * @param right Right hand side of subtraction
     * @return Result of subtraction
     */
    public static Sexpr subtract(Sexpr left, Sexpr right) {

	if (left.isConstant() && right.isConstant()) {
	    return new Constant(left.getValue() - right.getValue());
	}

	return new Subtraction(left, right);
    }

    /**
     * Multiply two expressions together
     *
     * @param left Left hand side of multiplication
     * @param right Right hand side of multiplication
     * @return Result of multiplication
     */
    public static Sexpr multiply(Sexpr left, Sexpr right) {

	if (left.isConstant() && right.isConstant()) {
	    return new Constant(left.getValue() * right.getValue());
	}

	return new Multiplication(left, right);
    }

    /**
     * Divide one expression with another
     *
     * @param denominator Denominator
     * @param nominator Nominator
     * @throws IllegalArgumentException Can't divide by 0.
     * @return Result of division
     */
    public static Sexpr divide(Sexpr denominator, Sexpr nominator) throws IllegalArgumentException {

	if (denominator.isConstant() && nominator.isConstant()) {
	    if (nominator.getValue() == 0) {
		throw new IllegalArgumentException("Can't divide by 0");
	    }

	    return new Constant(denominator.getValue() / nominator.getValue());
	}

	return new Division(denominator, nominator);
    }
}
