
import java.util.HashMap;

public class Variable extends Atom {

    private String ident;

    public Variable(String ident) {
	this.ident = ident;
    }

    public String getName() {
	return this.ident;
    }

    public Sexpr eval(HashMap<String, Sexpr> map) {
	return map.get(this.ident);
    }
}
