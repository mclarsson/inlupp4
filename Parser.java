import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.sql.SQLSyntaxErrorException;
import java.io.InputStream;

class Parser{
    StreamTokenizer st;

    Boolean debug = false;

    private String quit;
    private String vars;

    private ArrayList<String> reserved_words = new ArrayList<String>();

    /**
     * Constructor.
     *
     */
    public Parser(String quit, String vars, InputStream in){
        st = new StreamTokenizer(in);
        st.ordinaryChar('-');
	st.ordinaryChar('/');
	st.ordinaryChar('=');
        st.eolIsSignificant(true);

	this.quit = quit;
	this.vars = vars;

	this.reserved_words.add(this.quit);
	this.reserved_words.add(this.vars);
    }

    public Parser(InputStream in) {
	this("q", "vars", in);
    }

    public Parser() {
	this(System.in);
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

    public void flush() {
	if (st.ttype != st.TT_EOL) {
	    try {
		while (st.nextToken() != st.TT_EOL);
	    } catch (IOException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    /**
     * Checks whether the expression has "+", "-" or "=" in it and creates new operation accordingly.
     *
     * @throws IOException if input/output error occurs.
     * @return Expression.
     */
    public Sexpr expression() throws IOException {

	if (debug) System.out.println(" -- expression");

	Boolean valid = false;

	Sexpr expr = term();

        st.nextToken();

        if (expr.isVars() || expr.isQuit()) {
            return expr;
        }

        while (st.ttype == '+' || st.ttype == '-') {
	    valid = true;

	    if (st.ttype == '+') {
                expr = new Addition(expr, term());
            } else {
		expr = new Subtraction(expr, term());
	    }

	    st.nextToken();
        }

	while (st.ttype == '=') {
	    valid = true;
	    expr = new Assignment(expr, variable());
	    st.nextToken();
	}

	if (st.ttype == st.TT_EOL || st.ttype == ')') {
	    valid = true;
	}

	if (valid) {
	    return expr;
	} else {
	    throw new SyntaxErrorException("unexpected " + ((char) st.ttype));
	}
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

	    if (st.sval.equals(this.vars)) {
		return new Vars();
	    }

	    if (st.sval.equals(this.quit)) {
		return new Quit();
	    }

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

	    default:
		st.pushBack();
		return variable();
	    }

	    if (st.ttype != ')') {
		throw new SyntaxErrorException("expected ')' after operation");
	    } else {
		return unary;
	    }

	} else {
	    st.pushBack();
	    return atom();
	}
    }

    private Sexpr atom() throws IOException {
	if (debug) System.out.println(" -- atom");
	st.nextToken();

	if (st.ttype != st.TT_NUMBER && st.sval == null) {
	    throw new SyntaxErrorException("unexpected " + ((char) st.ttype));
	}

	if (st.ttype == st.TT_NUMBER) {
	    st.pushBack();
	    return constant();
	} else {
	    st.pushBack();
	    return variable();
	}
    }

    private Sexpr constant() throws IOException {
	if (debug) System.out.println(" -- constant");

	st.nextToken();
        if (st.ttype != st.TT_NUMBER) {
	    throw new SyntaxErrorException("expected constant, not: " + ((char) st.ttype));
	} else {
	    return new Constant(st.nval);
        }
    }

    private Sexpr variable() throws IOException {
	if (debug) System.out.println(" -- variable");

	st.nextToken();

	if (st.ttype != st.TT_WORD || st.sval == null) {
	    String m = st.ttype == st.TT_EOL ? " empty" : ": "+ ((char) st.ttype);
	    throw new SyntaxErrorException("expected variable name, not" + m);
        } else if (reserved_words.contains(st.sval)) {
	    throw new SyntaxErrorException("cannot write to reserved word: " + st.sval);
	} else {
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
