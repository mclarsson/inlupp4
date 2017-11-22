
public abstract class Binary extends Sexpr {
    protected Sexpr left;
    protected Sexpr right;

    public Binary(Sexpr left, Sexpr right) {
	this.left = left;
	this.right = right;
    }

    public String toString() {
	return "";
    }
}
