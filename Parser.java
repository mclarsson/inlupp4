import java.io.StreamTokenizer;
import java.io.IOException;

class Parser{
    StreamTokenizer st;

    public Parser(){
        st = new StreamTokenizer(System.in);
        st.ordinaryChar('-');
	st.ordinaryChar('/');
        st.eolIsSignificant(true);
    }

    public Sexpr expression() throws IOException{
	System.out.println(" -- expression");
        Sexpr expr = term();
	st.nextToken();
        while (st.ttype == '+' || st.ttype == '-') {

	    if (st.ttype == '+') {
                expr = new Addition(expr, term());
            } else {
		expr = new Subtraction(expr, term());
	    }

	    st.nextToken();
        }

	st.pushBack();

        return expr;
    }

    private Sexpr term() throws IOException {
	System.out.println(" -- term");
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
	System.out.println(" -- factor");
        Sexpr result;

	if (st.nextToken() != '(' && st.ttype != st.TT_WORD) {

            st.pushBack();
            result = atom();
        } else if (st.ttype == st.TT_WORD) {
	    System.out.println(" ---- " + st.sval);

	    switch (st.sval) {
	    case "sin":
		st.nextToken();
		result = new Sin(expression());
		break;
	    case "cos":
		st.nextToken();
		result = new Cos(expression());
		break;
	    case "log":
		st.nextToken();
		result = new Log(expression());
		break;
	    case "exp":
		st.nextToken();
		result = new Exp(expression());
		break;
	    default:
		throw new SyntaxErrorException("not a valid operation");
	    }

	    if (st.nextToken() != ')') {
                throw new SyntaxErrorException("expected ')'");
            }
	} else {
            result = expression();

            if (st.nextToken() != ')') {
                throw new SyntaxErrorException("expected ')'");
            }
        }

        return result;
    }

    private Sexpr atom() throws IOException {
	System.out.println(" -- atom");
	st.nextToken();
        if (st.ttype == st.TT_NUMBER) {
	    System.out.println(" ---- c " + st.nval);
	    return new Constant(st.nval);
        } else {
	    System.out.println(" ---- v " + st.sval);
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
