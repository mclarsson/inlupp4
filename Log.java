
import java.util.HashMap;

public class Log extends Unary {

    /**
     * constructor.
     *
     * @param argument Argument of operation.
     */
    public Log(Sexpr argument) {
	super(argument);
    }


    /**
     * Returns name of operation.
     *
     * @return Operation name.
     */
    public String getName() {
	return "Log";
    }

    /**
     * Evaluate current operation.
     *
     * @return Result
     */
    public Sexpr eval(HashMap<String, Sexpr> variables) {
	return Symbolic.log(argument.eval(variables));
    }
}
