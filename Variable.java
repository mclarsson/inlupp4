
import java.util.HashMap;

public class Variable extends Atom {

    private String ident;
    /**
     * Constructor.
     *
     * @param ident Identifier for variable.
     */
    public Variable(String ident) {
	this.ident = ident;
    }
    /**
     * Returns name of variable.
     *
     * @return Variable name.
     */
    public String getName() {
	return this.ident;
    }

    /**
     * Evaluate current Variable.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	if (variables.containsKey(this.getName())) {
            Sexpr expr = variables.get(this.ident);
            return expr.eval(variables);
	} else {
	    return this;
	}
    }
}
