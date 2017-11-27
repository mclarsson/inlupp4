import java.io.StreamTokenizer;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;

class Parser{
    StreamTokenizer st;

    Boolean debug = false;

    public Parser(){
        st = new StreamTokenizer(System.in);
        st.ordinaryChar('-');
	st.ordinaryChar('/');
	st.ordinaryChar('=');
        st.eolIsSignificant(true);
    }

    public Parser(String filename) throws IOException {
	Reader r = new FileReader(filename);
	st = new StreamTokenizer(r);
        st.ordinaryChar('-');
	st.ordinaryChar('/');
	st.ordinaryChar('=');
        st.eolIsSignificant(true);
    }

    public Sexpr expression() throws IOException {
	if (debug) System.out.println(" -- expression");

	Sexpr expr = term();
	st.nextToken();

        while (st.ttype == '+' || st.ttype == '-' || st.ttype == '=') {

	    if (st.ttype == '=') {
                expr = new Assignment(expr, expression());
		st.pushBack();
            } else if (st.ttype == '+') {
                expr = new Addition(expr, term());
            } else {
		expr = new Subtraction(expr, term());
	    }

	    st.nextToken();
        }

	//st.pushBack();

        return expr;
    }

    private Sexpr term() throws IOException {
	if (debug) System.out.println(" -- term");

	Sexpr term = factor();

        while (st.nextToken() == '*') {
	    term = new Multiplication(term, factor());
	}

	st.pushBack();

	while (st.nextToken() == '/') {
	    term = new Division(term, factor());
        }

	st.pushBack();

        return term;
    }

    private Sexpr factor() throws IOException{

	if (debug) System.out.println(" -- factor");

	if (st.nextToken() == '(') {

	    Sexpr expression = expression();

            if (st.ttype != ')') {
                throw new SyntaxErrorException("expected ')' after parenthesis");
            }

	    return expression;

	} else if (st.ttype == '-') {

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
    public SyntaxErrorException(){
        super();
    }
    public SyntaxErrorException(String msg){
        super(msg);
    }
}
