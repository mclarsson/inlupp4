import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;

class Parser{
    StreamTokenizer st;

    Boolean debug = false;

    /**
     * Constructor.
     *
     */
    public Parser(){
        st = new StreamTokenizer(System.in);
        st.ordinaryChar('-');
	st.ordinaryChar('/');
	st.ordinaryChar('=');
        st.eolIsSignificant(true);
    }

    /**
     * Constructor.
     *
     * @param filename File with test inputs.
     * @throws IOException If input/output error occurred.
     */
    public Parser(String filename) throws IOException {
	Reader r = new FileReader(filename);
	st = new StreamTokenizer(r);
        st.ordinaryChar('-');
	st.ordinaryChar('/');
	st.ordinaryChar('=');
        st.eolIsSignificant(true);
    }

    /**
     * Checks whether the expression has "+", "-" or "=" in it and creates new operation accordingly.
     *
     * @throws IOException if input/output error occurs.
     * @return Expression.
     */
    public Sexpr expression() throws IOException {

	if (debug) System.out.println(" -- expression");

	Sexpr expr = term();

        st.nextToken();

        if (expr.isVars() || expr.isQuit()) {
            return expr;
        }

        while (st.ttype == '+' || st.ttype == '-') {

	    if (st.ttype == '+') {
                expr = new Addition(expr, term());
            } else {
		expr = new Subtraction(expr, term());
	    }

	    st.nextToken();
        }

	while (st.ttype == '=') {
	    expr = new Assignment(expr, atom());
	    st.nextToken();
	}

	//st.pushBack();

        return expr;
    }

    /**
     * Checks if the term has "*" or "/" in it and creates a new operation accordingly.
     *
     * @throws IOException if input/output error occurs.
     * @return Term back to Expression.
     */
    private Sexpr term() throws IOException {
	if (debug) System.out.println(" -- term");

	Sexpr term = factor();

	st.nextToken();
	while (st.ttype == '*' || st.ttype == '/') {
	    if (st.ttype == '*') {
		term = new Multiplication(term, factor());
	    } else {
		term = new Division(term, factor());
	    }
	    st.nextToken();
	}

	st.pushBack();

        return term;
    }

    /**
     * Checks everything inside the parentheses for unary operations. If found, creates a new one.
     *
     * @throws IOException if input/output error occurs.
     * @return Expression, atom(Constant or Variable) or a Unary operation depending on input.
     */
    private Sexpr factor() throws IOException{

	if (debug) System.out.println(" -- factor");

	if (st.nextToken() == '(') {

	    Sexpr expression = expression();

            if (st.ttype != ')') {
                throw new SyntaxErrorException("expected ')' after parenthesis");
            }

	    return expression;
        }

        else if (st.ttype == '-') {

	    Sexpr neg = new Negation(term());
	    st.pushBack();
	    return neg;

	} else if (st.ttype == st.TT_WORD) {

	    Sexpr unary;

	    switch (st.sval) {
	    case "sin":
		st.nextToken();
		unary = new Sin(expression());
		break;

	    case "cos":
		st.nextToken();
		unary = new Cos(expression());
		break;

	    case "log":
		st.nextToken();
		unary = new Log(expression());
		break;

	    case "exp":
		st.nextToken();
		unary = new Exp(expression());
		break;
            case "vars":
                return new Vars();
            case "q":
                return new Quit();

	    default:
		// Get variable
		st.pushBack();
		return atom();
	    }

	    if (st.ttype != ')') {
		throw new SyntaxErrorException("expected ')' after operation");
	    } else {
		return unary;
	    }

	} else {
	    // Get constant
	    st.pushBack();
	    return atom();
	}
    }

    /**
     * Checks if the input is a Variable or a Constant.
     *
     * @throws IOException if input/output error occurs.
     * @return Constant or Variable depending on what the inpus was.
     */
    private Sexpr atom() throws IOException {
	if (debug) System.out.println(" -- atom");

	st.nextToken();
        if (st.ttype == st.TT_NUMBER) {
	    if (debug) System.out.println(" ---- c " + st.nval);

	    return new Constant(st.nval);
        } else {
	    if (debug) System.out.println(" ---- v " + st.sval);

	    return new Variable(st.sval);
	}
    }
}

class SyntaxErrorException extends RuntimeException{

    /**
     * Constructor.
     *
     */
    public SyntaxErrorException(){
        super();
    }

    /**
     * Constructor.
     *
     * @param msg The message to be displayed when error occurs.
     */
    public SyntaxErrorException(String msg){
        super(msg);
    }
}
